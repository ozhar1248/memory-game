/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author ozhar
 */
public class ReceivingMessages extends Thread
    {
        private Socket socket;
        private BlockingQueue<Package> qu_in;
        private int id;
        
        public ReceivingMessages(Socket socket, BlockingQueue<Package> in)
        {
            this.socket = socket;
            this.qu_in = in;
            id = -1;
        }
        
        public ReceivingMessages(Socket socket, BlockingQueue<Package> in, int id)
        {
            this.socket = socket;
            this.qu_in = in;
            this.id = id;
        }
        
        @Override
        public void run()
        {
            try
            {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String inputLine;

                while ((inputLine = in.readLine()) != null)
                {
                    Package newP = Package.conertToPackage(inputLine);
                    if (newP != null)
                    {
                        newP.setClientNumber(id);
                        qu_in.put(newP);
                    }
                    else
                    {
                        
                    }
                }
            }
            catch (IOException e)
            {

            }
            catch (InterruptedException e)
            {

            }

        }
    }
