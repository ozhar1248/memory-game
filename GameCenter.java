/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory.game;

import java.util.HashMap;
import java.util.concurrent.BlockingQueue;


/**
 *
 * @author ozhar
 */
public class GameCenter implements PackageReceiver{
    private IndexesBook clientNums;
    private BlockingQueue<Package> in;
    private BlockingQueue<Package> out;
    private HashMap<Integer, Game> clientGameMap;
    private int playerWaiting;
    static final int BOARD_SIZE = 4; // size^2 has to be even
    
    
    public GameCenter(BlockingQueue<Package> _in, BlockingQueue<Package> _out)
    {
        clientNums = new IndexesBook();
        in = _in;
        out = _out;
        clientGameMap = new HashMap<>();
        playerWaiting = -1;
    }
    
    public int addClient()
    {
        int new_id = clientNums.addIndex();
        if (playerWaiting < 0)
        {
            playerWaiting = new_id;
            //deliver message "waiting"
        }
        else
        {
            Game new_game = new Game(BOARD_SIZE, out, new_id, playerWaiting);
            clientGameMap.put(new_id, new_game);
            clientGameMap.put(playerWaiting, new_game);
            playerWaiting = -1;
        }
        return new_id;
    }
    
    public void ReceivePackage(Package _package)
    {
        int op = _package.getOpertionNumber();
        int id = _package.getClientNumber();
        switch (op)
        {
            case 2: int cardNum = Integer.parseInt(_package.getMessage());
                        chooseCard(id, cardNum);
                        break;
        }
    }
    
    private void chooseCard(int id, int card)
    {
        /***********************************stopped here***************/
        
    }
    
}
