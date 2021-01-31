package memory.game;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class ConnectToClient {
    private static final int PORT = 7777;
    private ServerSocket serverSocket;
    HashMap<Integer, Socket> sockets_map;
    ClientsGamesMap clientsGamesMap;
    
    public ConnectToClient(ClientsGamesMap clientsGames)
    {
        serverSocket = null;
        // Every client id is mapped to socket
        sockets_map = new HashMap<>();
        clientsGamesMap = clientsGames;
              
        createMainSocket();
        System.out.println("server ready");
        
        new SendingPackagesToClient(sockets_map).start();
        
        // TODO thread that listens to the server to the word exit
        
        listenToClients(); // busy wait loop
        
        
        try
        {
            serverSocket.close();
            //close all sockets
        }
        catch (IOException e)
        {
            System.out.println("Unable to close one of the sockets");
        }
    }
    
    private void createMainSocket()
    {
        try
        {
            serverSocket = new ServerSocket(PORT);
        }
        catch (IOException e)
        {
            System.out.println("Unable to connect");
            System.exit(1);
        }
    }
    
    private void listenToClients()
    {
        boolean listening = true;
        Socket socket = null;
        while (listening)
        {
            try
            {
                socket = serverSocket.accept();
                int new_id = clientsGamesMap.addClient();
                sockets_map.put(new_id, socket);
                new ReceivingMessages(socket, QueuesServer.in, new_id).start();
            }
            catch (IOException e)
            {
                System.out.println("Unable to connect one of the clients");
            }
        }
    }

    private static class SendingPackagesToClient extends Thread
    {
        HashMap<Integer, Socket> sockets_map;
        HashMap<Socket, PrintWriter> writers_map;
        
        public SendingPackagesToClient(HashMap<Integer, Socket> _sockets_map)
        {
            this.sockets_map = _sockets_map;
            writers_map = new HashMap<>();
        }
        
        @Override
        public void run() 
        {
            Package newP;
            Socket socket;
            
            //TODO when to stop it
            while (true)
            {
                try
                {
                    newP = QueuesServer.out.take();
                    socket = sockets_map.get(newP.getClientNumber());
                    PrintWriter out = writers_map.get(socket);
                    if (out == null)
                    {
                        out = new PrintWriter(socket.getOutputStream(), true);
                        out.flush();
                        writers_map.put(socket, out);
                    }
                    out.println(Package.ConvertToString(newP));
                }
                catch (InterruptedException | IOException e)
                {
                    System.out.println("Unable to write to client");
                    //TODO whats next
                }
                
            }
        }
    }
    
    
            
    
}
