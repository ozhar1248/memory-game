/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory.game;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author ozhar
 */
public class ClientWindow extends JFrame{
    private ArrayList<Card> cardsArray;
    private int size = 0;
    private BlockingQueue<Package> mail;
    
    
    public ClientWindow(int size, BlockingQueue<Package> mail) throws InterruptedException 
    {
        super();
        this.mail = mail;
        JPanel table = new JPanel();
        GridLayout grid = new GridLayout(size,size);
        grid.setVgap(50);
        grid.setHgap(50);
        table.setLayout(grid);
        cardsArray = new ArrayList<>();
        this.size = size;
        
        for (int i = 0; i < size*size; ++i)
        {
            Card card = new Card(i, mail);
            card.setBackground(Color.black);
            table.add(card);
            cardsArray.add(card);
        }
        super.add(table);
        
        
        setSize(900, 900);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    

    public void setTurn(boolean turn)
    {
        for (int i = 0; i < size*size; ++i)
        {
            cardsArray.get(i).setTurn(turn);
        }
    }
    
    public void showCard(int card, int indexColor)
    {
        cardsArray.get(card).setBackground(Colors.getColor(indexColor)); //bug
    }
    
    public void showOffCard(int index)
    {
        cardsArray.get(index).setBackground(Color.BLACK);
    }
    
    
    
    
    
}
