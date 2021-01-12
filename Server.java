/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory.game;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author ozhar
 */
public class Server {
    public static void main(String[] args)
    {
        BlockingQueue<Package> qu_in = new LinkedBlockingQueue<>();
        BlockingQueue<Package> qu_out = new LinkedBlockingQueue<>();
        
        GameCenter game_center = new GameCenter(qu_in, qu_out);
        ConnectToClient c = new ConnectToClient(qu_in, qu_out, game_center);
    }
    
}
