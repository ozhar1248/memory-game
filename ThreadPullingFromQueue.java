package memory.game;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/* Creates thread whose job is to pull packages from "in" queue*/
public class ThreadPullingFromQueue extends Thread
    {
        private final PackageReceiver center;
        private final BlockingQueue<Package> in_queue;

        public ThreadPullingFromQueue(PackageReceiver center, BlockingQueue<Package> in_queue) {
            this.center = center;
            this.in_queue = in_queue;
        }
        
        @Override
        public void run()
        {
            while (true)
            {
                try {
                    Package pack = in_queue.take();
                    center.ReceivePackage(pack);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
