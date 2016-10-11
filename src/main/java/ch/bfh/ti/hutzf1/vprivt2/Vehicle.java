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
    
    private final String ID = generateID();

    public Vehicle(ServiceProvider sp, PedersenScheme ps, Hash hash, Log log, int n, int s) {
        
        // Set vehicles license plate
        
        // Set variables
        //private int n = 0;
        //private int s = 0;
        int i = 0;

        final Element[] TAGS = new Element[n];
        final Element[] KEYS = new Element[s];
        final Element[][] DV = new Element [s][n];
        final Element[] DK = new Element[s];
        
        final ArrayList<String> HASHES = new ArrayList<>();
        
        log.console(ID + " starts registration phase");
        
        // Generate Fresh Tags
        log.console(ID + " generates fresh tags");
        
        for (int x = 0; x < n; x++) {
            TAGS[x] = ps.getMessage();
            log.console(ID + " tag: " + TAGS[x].getValue());
        }
        
        // Fresh Keys
        log.console(ID + " generates fresh keys");
        
        for (int x = 0; x < s; x++) {
            KEYS[x] = ps.getMessage();
            log.console (ID + " key: " + KEYS[x].getValue());
        }
  
        // Opening Keys for Tags
        log.console(ID + " generates opening keys for tags");
        
        for (int x = 0; x < s; x++) {
            for (int y = 0; y < n; y++) {
                DV[x][y] = ps.getKey();
                log.console(ID + " tag opening key: " + DV[x][y].getValue());
            }
        }
        
        // Opening Keys for Keys
        log.console(ID + " generate opening keys for keys");
        
        for (int x = 0; x < s; x++) {
            DK[x] = ps.getKey();
            log.console(ID + " key opening key: "+ DK[x].getValue());
        }
        
        final RoundPackage RI = new RoundPackage();
        log.console(ID + " generates round package");
        
        RI.addId(ID);
        log.console(ID + " adds ID " + ID + " to round package");
        
        RI.addRound(i);
        log.console(ID + " adds round " + i + " to round package");
        
        for(int x = 0; x < n; x++) {
            // ENCRYPTION MISSING HERE!!!
            //RI.addCommit(ps.commit(hash.Hash(TAGS[x], KEYS[i]), DV[i][x]));
            RI.addCommit(ps.commit(TAGS[x], DV[i][x]));
            log.console(ID + " adds commit of crypted " + TAGS[x].getValue() + " to round package");
        }
        
        sp.putDrivingData(RI);
        log.console(ID + " send round package to service proivder");
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
     /*
    public void setRound(int i) {
        this.i = i;
    }*/
    
    /*public String getDrivingTag() {
        return TAGS[];
    }*/
    
}
