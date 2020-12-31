package memory.game;

import java.security.SecureRandom;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ozhar
 */
public class Board {
    private final int[] board;
    private final int size;
    private final int TAKEN = -1;
    private int countTaken;
    Board(int n)
    { 
        if (n*n %2 == 1)
        {
            //throw exception
        }
        
        size = n;
        countTaken = n*n;
        board = new int[n*n];
        randomizeBoard();
        
    }
    
    // row- between 1 and n, also col
    //return 1 if change has been made and 0 otherwise
    public int move(int row1, int col1, int row2, int col2)
    {
        assert(!(row1 > size || row1 < 0 || row2 > size || row2 < 0
                || col1 > size || col1 < 0 || col2 > size || col2 < 0));
        if (lookUp(row1,col1) == lookUp(row2, col2) && lookUp(row1,col1) != TAKEN)
        {
            setBoard(row1, col1, TAKEN);
            setBoard(row2, col2, TAKEN);
            countTaken -= 2;
            return 1;
        }
        return 0;
        
    }
    
    public boolean onGame()
    {
        return countTaken > 0;
    }
    
    private int lookUp(int row, int col)
    {
        return board[(row-1)*size -1 +col];
    }
    
    private void setBoard(int row, int col, int num)
    {
        board[(row-1)*size -1 +col] = num;
    }
    
    public void printBoard()
    {
        for (int i = 0; i < size*size; ++i)
        {
            System.out.print(board[i]+" ");
            if ((i+1) % size == 0)
            {
                System.out.println("");
            }
        }
    }
    private void randomizeBoard()
    {
        int[] numsCollection = new int[size*size];
        for (int i = 0; i < size*size; ++i)
        {
            numsCollection[i] = i;
        }
        
        int endIndex = size*size;
        for (int i = 0; i < size*size; ++i)
        {
            SecureRandom rand = new SecureRandom();
            int randIndex = rand.nextInt(endIndex); //range [0,endIndex)
            int randNum = numsCollection[randIndex]%(size*size/2);
            endIndex--;
            numsCollection[randIndex] = numsCollection[endIndex];
            board[i] = randNum;
        }
    }
    
}
