/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory.game;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 *
 * @author ozhar
 */
public class ClientsBook {
    private ArrayList<Integer> list_ids;
    private PriorityQueue<Integer> next_cancelled;
    
    public ClientsBook()
    {
        list_ids = new ArrayList<>();
        next_cancelled = new PriorityQueue<>();
    }
    
    public int addClient()
    {
        int next;
        
        if (next_cancelled.isEmpty())
        {
            next = list_ids.size();
        }
        else
        {
            next = next_cancelled.poll();
        }
        list_ids.add(next);
        return next;
    }
    
    public void removeClient(int _id)
    {
        list_ids.remove(_id);
        next_cancelled.offer(_id);
    }
    
    public boolean isEmpty()
    {
        return list_ids.isEmpty();
    }
}
