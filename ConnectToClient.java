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
        
        SendingPackages sender = new SendingPackages(_out, sockets_map);
        sender.start();
        
        Socket socket = null;
        while (listening)
        {
            try
            {
                socket = serverSocket.accept();
                int new_id = _center.addClient();
                sockets_map.put(new_id, socket);
                
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

    private static class DealingWithClient extends Thread{

        private Socket m_socket;
        
        public DealingWithClient(Socket socket) 
        {
            m_socket = socket;
        }

        @Override
        public void run() {
            try
            {
                PrintWriter out = new PrintWriter(m_socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(m_socket.getInputStream()));
                
                String inputLine;
                while ((inputLine = in.readLine()) != null)
                {
                    out.println("From server: "+inputLine);
                }
                out.close();
                in.close();
                m_socket.close();
                
            }
            catch (IOException e)
            {
                
            }
            
            
            
        }
    }
    
    private static class SendingPackages extends Thread
    {
        BlockingQueue<Package> out;
        HashMap<Integer, Socket> sockets_map;
        HashMap<Socket, PrintWriter> writers_map;
        
        public SendingPackages(BlockingQueue<Package> _out, HashMap<Integer, Socket> _sockets_map)
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
                    socket = sockets_map.get(newP.getClientNumber());
                    PrintWriter out = writers_map.get(socket);
                    if (out == null)
                    {
                        out = new PrintWriter(socket.getOutputStream(), true);
                        writers_map.put(socket, out);
                    }
                    out.println(newP.getClientNumber()+"#"+newP.getOpertionNumber()
                            +"#"+newP.getMessage());
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
