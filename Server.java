/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory.game;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ozhar
 */
public class Server {
    public static void main(String[] args)
    {
        BlockingQueue<Package> qu_in = new LinkedBlockingQueue<>();
        BlockingQueue<Package> qu_out = new LinkedBlockingQueue<>();
        
        int size = Colors.N;
        //send size
        GameCenter game_center = new GameCenter(qu_in, qu_out);
        new ThreadReceivingPackages(game_center, qu_in).start();
        ConnectToClient c = new ConnectToClient(qu_in, qu_out, game_center);
    }
    
    
    
}


