/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Queue;
import javax.swing.JPanel;

/**
 *
 * @author ozhar
 */
public class Card extends JPanel{
    private int m_i;
    
    public Card(int i)
    {
        m_i = i;
        
        addMouseListener(new MouseAdapter()
            {
                public void mousePressed(MouseEvent e)
                {
                    System.out.println(m_i);
                }
            });
    }
    
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        super.setBackground(Color.black);
    }
    
}
