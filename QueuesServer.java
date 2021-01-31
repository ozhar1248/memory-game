package memory.game;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class QueuesServer {
    //global queue for receiving packages
    public static BlockingQueue<Package> in = new LinkedBlockingQueue<>();
    //global queue for sending packages
    public static BlockingQueue<Package> out = new LinkedBlockingQueue<>();
}
