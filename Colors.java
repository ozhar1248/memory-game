/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory.game;

import java.awt.Color;

/**
 *
 * @author ozhar
 */
public class Colors {
    private static Color colors[] = new Color[] {Color.BLUE, Color.CYAN, Color.GREEN, Color.WHITE,
        Color.RED, Color.ORANGE, Color.YELLOW, Color.PINK};
    public static final int N = 4; //N*N should be even
    
    public static Color getColor(int index)
    {
        return colors[index];
    }
    
}
