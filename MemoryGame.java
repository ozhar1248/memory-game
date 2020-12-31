/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory.game;

import java.util.Scanner;

/**
 *
 * @author ozhar
 */
public class MemoryGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Board b = new Board(4);
        Scanner s = new Scanner(System.in);
        while (b.onGame())
        {
            b.printBoard();
            System.out.println("********");
            int row1 = s.nextInt();
            int col1 = s.nextInt();
            int row2 = s.nextInt();
            int col2 = s.nextInt();
            b.move(row1, col1, row2, col2);
        }
        
    }
    
}
