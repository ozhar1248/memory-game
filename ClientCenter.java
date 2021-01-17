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
public class ClientCenter implements PackageReceiver{
    private BlockingQueue<Package> qu_out;
    ClientWindow game;
    
    public ClientCenter(BlockingQueue<Package> qu_out)
    {
        this.qu_out = qu_out;
        game = null;
    }
    public void ReceivePackage(Package _package)
    {
        int card;
        int op = _package.getOpertionNumber();
        switch (op)
        {
            case 0: openNewGame(_package);
                    break;
            case 1: changeTurn(_package);
                    break;
            case 3: String[] tokens = _package.getMessage().split(" ");
                    card = Integer.parseInt(tokens[0]);
                    int indexColor = Integer.parseInt(tokens[1]);
                    game.showCard(card, indexColor);
                    break;
            case 4: card = Integer.parseInt(_package.getMessage());
                    showOffCard(card);
                    break;
            case 5: String message = _package.getMessage();
                    endGame(message);
                
        }
    }
    
    private void openNewGame(Package _package)
    {
        int size = Integer.parseInt(_package.getMessage());
        //check if its not null
        try {

            game = new ClientWindow(size, qu_out);
        } catch (InterruptedException ex) {
            Logger.getLogger(ClientCenter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void changeTurn(Package _package)
    {
        int turn = Integer.parseInt(_package.getMessage());
        if (turn == 1)
        {
            game.setTurn(true);
        }
        else
        {
            game.setTurn(false);
        }
    }
   
    
    private void showOffCard(int num)
    {
        game.showOffCard(num);
    }
    
    private void endGame(String outcome)
    {
        // to complete
    }
    
}
