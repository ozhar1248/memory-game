package memory.game;

import java.awt.Color;
import java.awt.GridLayout;import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ClientWindow extends JFrame{
    private final ArrayList<Card> cardsArray;
    private final int size;
    
    public ClientWindow(int size) 
    {
        super();
        this.size = size;
        JPanel table = new JPanel();
        JPanel board = new JPanel();
        GridLayout grid = new GridLayout(size,size);
        grid.setVgap(50);
        grid.setHgap(50);
        table.setLayout(grid);
        JLabel message = new JLabel("");
        JLabel turn = new JLabel("");
        JButton quitButton = new JButton("Quit Game");
        board.add(message);
        board.add(turn);
        board.add(quitButton);
        cardsArray = new ArrayList<>();
        
        
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
