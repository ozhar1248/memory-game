/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory.game;

import java.util.concurrent.BlockingQueue;

/**
 *
 * @author ozhar
 */
public class GameCenter {
    private ClientsBook clientNums;
    private BlockingQueue<Package> in;
    private BlockingQueue<Package> out;
    
    
    public GameCenter(BlockingQueue<Package> _in, BlockingQueue<Package> _out)
    {
        clientNums = new ClientsBook();
        in = _in;
        out = _out;
    }
    
    public int addClient()
    {
        int new_id = clientNums.addClient();
        Package new_pac = new Package(new_id, 0, "welcome!");
        try
        {
            out.put(new_pac);
        }
        catch (InterruptedException e)
        {
            
        }
        return new_id;
    }
    
}
