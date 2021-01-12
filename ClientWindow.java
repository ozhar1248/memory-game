/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory.game;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author ozhar
 */
public class ClientWindow extends JFrame{
    public ClientWindow() throws InterruptedException 
    {
        super();
        JPanel table = new JPanel();
        GridLayout grid = new GridLayout(4,4);
        grid.setVgap(50);
        grid.setHgap(50);
        table.setLayout(grid);
        
        for (int i = 0; i < 16; ++i)
        {
            Card card = new Card(i);
            table.add(card);
        }
        super.add(table);
        
        
        setSize(900, 900);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    
    
    
    
}
