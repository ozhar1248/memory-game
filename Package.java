/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory.game;

/**
 * clientNumber-    equals -1 if a package from the Client to the Server
 * 
 * operator-        0 : create board. message is the size of the board
 *                  1 : turn. if message is "1" so it is your turn and "0" otherwise
 *                  2 : the client chose a card. message is the card number
 * @author ozhar
 */
public class Package {
    private int clientNumber;
    private int opertionNumber;
    private String message;

    public Package(int clientNumber, int opertionNumber, String message) {
        this.clientNumber = clientNumber;
        this.opertionNumber = opertionNumber;
        this.message = message;
    }

    public void setClientNumber(int clientNumber) {
        this.clientNumber = clientNumber;
    }

    public void setOpertionNumber(int opertionNumber) {
        this.opertionNumber = opertionNumber;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getClientNumber() {
        return clientNumber;
    }

    public int getOpertionNumber() {
        return opertionNumber;
    }

    public String getMessage() {
        return message;
    }
    
    public String toString()
    {
        return Package.ConvertToString(this);
    }
    
    public static String ConvertToString(Package p)
    {
        return p.getClientNumber()+"#"+p.getOpertionNumber()+"#"+p.getMessage();
    }
    
    public static Package conertToPackage(String s)
    {
        String[] tokens = s.split("#");
        int size = tokens.length;
        if (size != 3)
        {
            return null;
        }
        try
        {
            int cliNum = Integer.parseInt(tokens[0]);
            int operNum = Integer.parseInt(tokens[1]);
            return new Package(cliNum, operNum, tokens[2]);
        }
        catch (NumberFormatException e)
        {
            return null;
        }
        
    }
}
