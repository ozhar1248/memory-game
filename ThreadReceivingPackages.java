/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory.game;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ozhar
 */
public class ThreadReceivingPackages extends Thread
    {
        private PackageReceiver center;
        private BlockingQueue<Package> in;

        public ThreadReceivingPackages(PackageReceiver center, BlockingQueue<Package> in) {
            this.center = center;
            this.in = in;
        }
        
        @Override
        public void run()
        {
            while (true)
            {
                try {
                    Package pack = in.take();
                    center.ReceivePackage(pack);
                    //deliver the packsge to the center
                } catch (InterruptedException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
