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
    //return value if the user was right
    // return -1 otherwise
    public int move(int i1, int i2)
    {
        if (board[i1] == board[i2])
        {
            int val = board[i1];
            board[i1] = TAKEN;
            board[i2] = TAKEN;
            countTaken -= 2;
            return val;
        }
        return -1;
    }
    
    public boolean onGame()
    {
        return countTaken > 0;
    }
    
    public boolean isTaken(int index)
    {
        return board[index] == TAKEN;
    }
    
    public int lookUp(int index)
    {
        return board[index];
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
