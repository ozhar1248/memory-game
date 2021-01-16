/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ozhar
 */
public class ConnectToServer {
    public ConnectToServer(String _host, BlockingQueue<Package> qu_in, BlockingQueue<Package> qu_out)
    {
        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        String host = _host;
        try
        {
            socket = new Socket(host, 7777);
            new SendingPackagesToServer(qu_out, socket).start();
            new ReceivingMessages(socket, qu_in).start();
        }
        catch (IOException e)
        {
            
        }
        System.out.println("Connected");
    }
    
    private static class SendingPackagesToServer extends Thread
    {
        BlockingQueue<Package> qu_out;
        Socket socket;
        PrintWriter out;
        
        public SendingPackagesToServer(BlockingQueue<Package> qu_out, Socket socket) 
        {
            
            try {
                this.qu_out = qu_out;
                this.socket = socket;
                out = new PrintWriter(socket.getOutputStream(), true);
                out.flush();
            } catch (IOException ex) {
                Logger.getLogger(ConnectToServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        @Override
        public void run()
        {
            try 
            {
                while (true)
                {
                    Package newP;
                    newP = qu_out.take();
                    out.println(Package.ConvertToString(newP));
                }
            } 
            catch (InterruptedException ex) 
            {
                Logger.getLogger(ConnectToServer.class.getName()).log(Level.SEVERE, null, ex);
            }    
        }
    }
}


