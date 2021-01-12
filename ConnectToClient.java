package memory.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author ozhar
 */
public class ConnectToClient {
    
    public ConnectToClient()
    {
        ServerSocket serverSocket = null;
        boolean listening = true;
        try
        {
            serverSocket = new ServerSocket(7777);
        }
        catch (IOException e)
        {
            
        }
        System.out.println("server ready");
        
        Socket socket = null;
        while (listening)
        {
            try
            {
                socket = serverSocket.accept();
                new DealingWithClient(socket).start();
                
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
    
}
