/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory.game;

/**
 *
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
}
