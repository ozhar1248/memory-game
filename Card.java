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
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author ozhar
 */
public class Card extends JPanel{
    private int m_i;
    private boolean turn;
    private BlockingQueue<Package> mail;
    
    public Card(int i, BlockingQueue<Package> mail)
    {
        m_i = i;
        this.mail = mail;
        super.setBackground(Color.black);
        turn = false;
        
        addMouseListener(new MouseAdapter()
            {
                public void mousePressed(MouseEvent e)
                {
                    
                    if (!turn)
                    {
                        return;
                    }
                    try {
                        mail.put(new Package(-1, 2, ""+m_i));
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Card.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
    }
    
    public void setTurn(boolean turn)
    {
        this.turn = turn;
    }
    
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
    }
    
    public void changeBG(Color c)
    {
        super.setBackground(c);
    }
    
}
