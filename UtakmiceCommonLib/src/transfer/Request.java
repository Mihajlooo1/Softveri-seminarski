/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer;

import java.io.Serializable;


public class Request implements Serializable{
    private Object podaci;
    private int operation;

    public Request() {
    }

    public Request(Object podaci, int operation) {
        this.podaci = podaci;
        this.operation = operation;
    }

    public int getOperation() {
        return operation;
    }

    public Object getPodaci() {
        return podaci;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }

    public void setPodaci(Object podaci) {
        this.podaci = podaci;
    }
    
    
}
