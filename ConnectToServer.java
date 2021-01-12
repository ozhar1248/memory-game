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

/**
 *
 * @author ozhar
 */
public class ConnectToServer {
    public ConnectToServer(String _host)
    {
        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        String host = _host;
        try
        {
            socket = new Socket(host, 7777);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch (UnknownHostException e)
        {
            
        }
        catch (IOException e)
        {
            
        }
        System.out.println("Connected");
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput;
        try
        {
            while ((userInput = stdIn.readLine()) != null)
            {
                out.println(userInput);
                System.out.println(in.readLine());
            }
            out.close();
            in.close();
            stdIn.close();
            socket.close();
        }
        catch (IOException e)
        {
            
        }
        
        
        
        
        
    }
    
}
