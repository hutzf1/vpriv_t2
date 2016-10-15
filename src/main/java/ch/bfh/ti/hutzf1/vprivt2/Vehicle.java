/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.ti.hutzf1.vprivt2;

import ch.bfh.unicrypt.math.algebra.general.interfaces.Element;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author fh
 */

public class Vehicle {
    
    private final String ID;
    private final ArrayList<Element> TAGS;
    private final ArrayList<Element> KEYS;
    private final ArrayList<Element> DV;
    private final ArrayList<Element> DK;
    private final ArrayList<Element> DC;
    
    private final int i;
    private final int n;
    private final int s;

    public Vehicle(ServiceProvider sp, PedersenScheme ps, Hash hash, Log log, int n, int s) {
        
        // Set vehicles license plate
        ID = generateID();
        
        // Set parameter
        i = 0;
        this.n = n;
        this.s = s;
        
        // Set tags, keys, opening keys
        TAGS = new ArrayList<>();
        KEYS = new ArrayList<>();
        DV = new ArrayList<>();
        DK = new ArrayList<>();
        DC = new ArrayList<>();
        
        //final ArrayList<String> HASHES = new ArrayList<>();
        
        // Start vehicle registration
        log.console(ID + " starts registration phase");
        
        // Generate Fresh Tags
        log.console(ID + " generates fresh tags");
        
        for (int x = 0; x < this.n; x++) {
            TAGS.add(ps.getTag());
            log.console(ID + " tag: " + TAGS.get(x).getValue());
        }
        
        // Fresh Keys
        log.console(ID + " generates fresh keys");
        
        for (int x = 0; x < this.s; x++) {
            KEYS.add(ps.getKey());
            log.console (ID + " key: " + KEYS.get(x).getValue());
        }
  
        // Opening Keys for Tags
        log.console(ID + " generates opening keys for tags");
        
        for (int x = 0; x < this.s; x++) {
            for (int y = 0; y < n; y++) {
                int index = x * y + y;
                DV.add(ps.getOpeningKey());
                log.console(ID + " tag opening key: " + DV.get(index).getValue());
            }
        }
        
        // Opening Keys for Keys
        log.console(ID + " generate opening keys for keys");
        
        for (int x = 0; x < this.s; x++) {
            DK.add(ps.getOpeningKey());
            log.console(ID + " key opening key: "+ DK.get(x).getValue());
        }
        
        // Generate round package to send data to service provider
        final RoundPackage RI = new RoundPackage();
        log.console(ID + " generates round package");
        
        RI.setId(ID);
        log.console(ID + " adds ID " + ID + " to round package");
        
        RI.setRound(i);
        log.console(ID + " adds round " + i + " to round package");
        
        RI.setKey(ps.commit(KEYS.get(i), DK.get(i)));
        log.console(ID + " adds key " + KEYS.get(i) + " to round package");
        
        for(int x = 0; x < this.n; x++) {
            // ENCRYPTION MISSING HERE!!!
            //RI.addCommit(ps.commit(hash.Hash(TAGS[x], KEYS[i]), DV[i][x]));
            int index = x * i + i;
            RI.addCommit(ps.commit(hash.getHash(TAGS.get(x), DK.get(i)), DV.get(index)));
            log.console(ID + " adds commit of crypted " + TAGS.get(x).getValue() + " to round package");
        }
        
        sp.putVehicleData(RI);
        log.console(ID + " send round package to service provider");
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
    
    public Element getRandomTag() {
        Random rand = new Random();
        return TAGS.get(rand.nextInt(this.n));
    }
    
    public Element getKey(int round) {
        return KEYS.get(round);
    }

    public void drive(ServiceProvider sp, Log log) {
        Location currentLocation = new Location();
        Date timestamp = new Date();
        Element randomTag = this.getRandomTag();
        int toll = sp.putDrivingData(randomTag, currentLocation, timestamp);
        log.console(ID + " is driving. Tag " + randomTag.getValue() + " (" + currentLocation.LATIDUDE + ", " + currentLocation.LONGITUDE + ") - " + timestamp);
        log.console(ID + " value of a toll station is " + toll);
    }

    public void calcCost(ServiceProvider sp, Log log) {
        int c = 0;
        
        ArrayList<DrivingTuple> W = sp.getAllTags();

        log.console(ID + " is calculating cost...");
        for (DrivingTuple dr : W) {
            if(TAGS.contains(dr.tag)) {
                log.console(dr.tag.getValue().toString());
                c += dr.cost;
            }   
        }
        log.console(ID + " calculated " + c);
        sp.putCostData(ID, c);
    }

    
     /*
    public void setRound(int i) {
        this.i = i;
    }*/
    
    /*public String getDrivingTag() {
        return TAGS[];
    }*/
    
}
