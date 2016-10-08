/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.ti.hutzf1.vprivt2;

import java.util.Random;

/**
 *
 * @author fh
 */
public class Vehicle {
    
    // Set vehicles license plate
    private String ID = GenerateID();
    // Set variables
    private int n = 0;
    private int s = 0;
    
    private String GenerateID() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVW0123456789";
        int length = 8;
        
        Random rand = new Random();
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < length; i++) {
            buf.append(chars.charAt(rand.nextInt(chars.length())));
        }
        return buf.toString();
    }
    
    public String GetId() {
        return ID;
    }
    
    public void SetVariables(int n, int s) {
        this.n = n;
        this.s = s;
    }
    
}
