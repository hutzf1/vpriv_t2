/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.ti.hutzf1.vprivt2;

import ch.bfh.unicrypt.math.algebra.general.interfaces.Element;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author fh
 */

public class Vehicle {
    
    // Set vehicles license plate
    private final String ID = generateID();
    // Set variables
    private int n = 0;
    private int s = 0;
    private int i = 0;
    
    private final Element[] TAGS = new Element[n];
    private final Element[] KEYS = new Element[s];
    private final Element[][] DV = new Element [s][n];
    private final Element[] DK = new Element[s];
     
    private final ArrayList<String> HASHES = new ArrayList<>();
    
    public Vehicle(ServiceProvider sp, PedersenScheme ps, Hash hash) {
        
        //Log("Start Registration Phase");
        
        // Generate Fresh Tags
        //Log("Generate Fresh Tags");
        
        for (int x = 0; x < n; x++) {
            TAGS[x] = ps.getMessage();
            //Log("Tag: " + V[x]);
        }
        
        // Fresh Keys
        //Log("Generate Fresh Keys");
        
        for (int x = 0; x < s; x++) {
            KEYS[x] = ps.getMessage();
            //Log("Key: " + K[y]);
        }
  
        // Opening Keys for Tags
        //Log("Generate Opening Keys for Tags");
        
        for (int x = 0; x < s; x++) {
            for (int y = 0; y < n; y++) {
                DV[x][y] = ps.getKey();
                //Log("Tag OK: " + DV[x][y]);
            }
        }
        
        // Opening Keys for Keys
        //Log("Generate Opening Keys for Keys");
        
        for (int x = 0; x < s; x++) {
            DK[x] = ps.getKey();
            //Log("Key OK: "+ DK[x]);
        }
        
        final RoundPackage RI = new RoundPackage();

        RI.addId(ID);
        RI.addRound(i);
        for(int x = 0; x < n; x++) {
            RI.addCommit(ps.commit(hash.Hash(TAGS[x], KEYS[i]), DV[i][x]));
        }
    }
    
    private String generateID() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVW0123456789";
        int length = 8;
        
        Random rand = new Random();
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < length; i++) {
            buf.append(chars.charAt(rand.nextInt(chars.length())));
        }
        return buf.toString();
    }
    
    public String getId() {
        return ID;
    }
    
    public void setVariables(int n, int s) {
        this.n = n;
        this.s = s;
    }
    
    public void setRound(int i) {
        this.i = i;
    }
    
    /*public String getDrivingTag() {
        return TAGS[];
    }*/
    
}
