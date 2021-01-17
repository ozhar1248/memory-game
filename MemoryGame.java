/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory.game;

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author ozhar
 */
public class MemoryGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        BlockingQueue<Package> qu_in = new LinkedBlockingQueue<>();
        BlockingQueue<Package> qu_out = new LinkedBlockingQueue<>();
        int size = Colors.N;
        //send size
        PackageReceiver center = new ClientCenter(qu_out);
        new ThreadReceivingPackages(center, qu_in).start();
        ConnectToServer c = new ConnectToServer("localhost", qu_in, qu_out);
        
        
    }
    
}
