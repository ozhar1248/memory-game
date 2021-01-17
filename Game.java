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
    private int points1;
    private int points2;
    private int turn;
    private int size;
    private int lastCard;
    private boolean finishedTurn;
    
    public Game(int size, BlockingQueue<Package> qu_out, int player1, int player2)
    {
        
        try {
            finishedTurn = true;
            
            gameBoard = new Board(size);
            mail_out = qu_out;
            this.player1 = player1;
            this.player2 = player2;
            points1 = 0;
            points2 = 0;
            
            this.size = size;
            mail_out.put(new Package(player1,0,""+size));
            mail_out.put(new Package(player2,0,""+size));
            lastCard = -1;
            turn = player1;
            turnOn(player1);
            turnOff(player2);
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void turnOn(int player)
    {
        try {
            mail_out.put(new Package(player,1,"1"));
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void turnOff(int player)
    {
        try {
            mail_out.put(new Package(player,1,"0"));
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void switchTurn()
    {
        if (turn == player1)
        {
            turn = player2;
            turnOff(player1);
            turnOn(player2);
        }
        else
        {
            turn = player1;
            turnOff(player2);
            turnOn(player1);
        }
    }
    

    
    public void chooseCard(int card)
    {
        int res = 0;
        
        if (gameBoard.isTaken(card))
        {
            return;
        }
        int val = gameBoard.lookUp(card);
        
        if (finishedTurn)
        {
            finishedTurn = false;
        }
        else
        {
            res = gameBoard.move(lastCard, card);
            finishedTurn = true;
        }
        try {
            mail_out.put(new Package(player1,3,card+" "+val));
            mail_out.put(new Package(player2,3,card+" "+val));
            if (res >= 0 && finishedTurn)
            {
                if (turn == player1)
                {
                    points1++;
                    //send update about score
                }
                else
                {
                    points2++;
                    //send update about score
                }
            }
            if (!gameBoard.onGame())
            {
                turnOff(player1);
                turnOff(player2);
                if (points1 > points2)
                {
                    mail_out.put(new Package(player2,5,"lost"));
                    mail_out.put(new Package(player1,5,"win"));
                }
                if (points1 < points2)
                {
                    mail_out.put(new Package(player1,5,"lost"));
                    mail_out.put(new Package(player2,5,"win"));
                }
                if (points1 == points2)
                {
                    mail_out.put(new Package(player1,5,"even"));
                    mail_out.put(new Package(player2,5,"even"));
                }
            }
            if (res == -1 && finishedTurn) //user was wrong
            {
                turnOff(player1);
                turnOff(player2);
                Thread.sleep(1000);
                mail_out.put(new Package(player1,4,""+card));
                mail_out.put(new Package(player2,4,""+card));
                mail_out.put(new Package(player1,4,""+lastCard));
                mail_out.put(new Package(player2,4,""+lastCard));
                turnOff(player1);
                turnOff(player2);
                switchTurn();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        lastCard = card;
        
        
    }
    
}
