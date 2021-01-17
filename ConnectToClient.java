package memory.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author ozhar
 */
public class ConnectToClient {
    
    public ConnectToClient(BlockingQueue<Package> _in, BlockingQueue<Package> _out, GameCenter _center)
    {
        ServerSocket serverSocket = null;
        HashMap<Integer, Socket> sockets_map = new HashMap<>();
        
        boolean listening = true;
        try
        {
            serverSocket = new ServerSocket(7777);
        }
        catch (IOException e)
        {
            
        }
        System.out.println("server ready");
        
        new SendingPackagesToClient(_out, sockets_map).start();
        
        
        Socket socket = null;
        while (listening)
        {
            try
            {
                socket = serverSocket.accept();
                int new_id = _center.addClient();
                sockets_map.put(new_id, socket);
                new ReceivingMessages(socket, _in, new_id).start();
            }
            catch (IOException e)
            {

            }
        }
        
        try
        {
            serverSocket.close();
        }
        catch (IOException e)
        {

        }
    }

    private static class SendingPackagesToClient extends Thread
    {
        BlockingQueue<Package> out;
        HashMap<Integer, Socket> sockets_map;
        HashMap<Socket, PrintWriter> writers_map;
        
        public SendingPackagesToClient(BlockingQueue<Package> _out, HashMap<Integer, Socket> _sockets_map)
        {
            this.out = _out;
            this.sockets_map = _sockets_map;
            writers_map = new HashMap<>();
        }
        
        @Override
        public void run()
        {
            Package newP;
            Socket socket;
            
            while (true)
            {
                try
                {
                    newP = out.take();
                    System.out.println("#server: pull from queue");
                    socket = sockets_map.get(newP.getClientNumber());
                    PrintWriter out = writers_map.get(socket);
                    if (out == null)
                    {
                        out = new PrintWriter(socket.getOutputStream(), true);
                        out.flush();
                        writers_map.put(socket, out);
                    }
                    out.println(Package.ConvertToString(newP));
                    System.out.println("#server: sent to client");
                }
                catch (InterruptedException e)
                {
                    
                }
                catch (IOException e)
                {
                    
                }
                
            }
        }
    }
    
    
            
    
}
