/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory.game;

import java.awt.Color;
import java.security.SecureRandom;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ozhar
 */
public class Game {
    private Board gameBoard;
    private BlockingQueue<Package> mail_out;
    private int player1;
    private int player2;
    private int size;
    Color colors[];
    
    public Game(int size, BlockingQueue<Package> qu_out, int player1, int player2)
    {
        
        try {
            gameBoard = new Board(size);
            mail_out = qu_out;
            this.player1 = player1;
            this.player2 = player2;
            this.size = size;
            mail_out.put(new Package(player1,0,""+size));
            mail_out.put(new Package(player2,0,""+size));
            startGame();
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void changeTurn(int nextPlayer)
    {
        try {
            if (nextPlayer == player1)
            {
                mail_out.put(new Package(player1,1,"1"));
                mail_out.put(new Package(player2,1,"0"));
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void startGame()
    {
        changeTurn(player1);
    }
    
    private void fillColorsArray()
    {
        colors = new Color[] {Color.BLUE, Color.CYAN, Color.GREEN, Color.WHITE,
        Color.RED, Color.ORANGE, Color.YELLOW, Color.PINK};
    }
    
}
