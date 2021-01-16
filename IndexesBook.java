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
public class IndexesBook {
    private ArrayList<Integer> list_ids;
    private PriorityQueue<Integer> next_cancelled;
    
    public IndexesBook()
    {
        list_ids = new ArrayList<>();
        next_cancelled = new PriorityQueue<>();
    }
    
    public int addIndex()
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
    
    public void removeIndex(int _id)
    {
        list_ids.remove(_id);
        next_cancelled.offer(_id);
    }
    
    public boolean isEmpty()
    {
        return list_ids.isEmpty();
    }
}
