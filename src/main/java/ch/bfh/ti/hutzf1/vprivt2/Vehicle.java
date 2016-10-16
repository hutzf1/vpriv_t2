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

public final class Vehicle {
    
    private final String ID;
    private final ArrayList<Element> TAGS;
    private final ArrayList<Element> KEYS;
    private final ArrayList<Element> DV;
    private final ArrayList<Element> DK;
    private final ArrayList<Element> DC;
    
    private final int i;
    private final int n;
    private final int s;
    
    private final ServiceProvider sp;
    private final PedersenScheme ps;
    private final Hash hash;
    private final Log log;

    public Vehicle(ServiceProvider sp, PedersenScheme ps, Hash hash, Log log, int n, int s) {
        
        this.sp = sp;
        this.ps = ps;
        this.hash = hash;
        this.log = log;
        
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
        
        log.console(ID);
        this.registration();
    }
    
    private String generateID() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVW0123456789";
        int length = 8;
        
        Random rand = new Random();
        StringBuilder buf = new StringBuilder();
        for (int x = 0; x < length; x++) {
            buf.append(chars.charAt(rand.nextInt(chars.length())));
        }
        return buf.toString();
    }
    
    public Element getRandomTag() {
        Random rand = new Random();
        return TAGS.get(rand.nextInt(this.n));
    }
    
    public Element getKey(int round) {
        return KEYS.get(round);
    }

    private void registration() {
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
    
    public void drive() {
        Location currentLocation = new Location();
        Date timestamp = new Date();
        Element randomTag = this.getRandomTag();
        int toll = sp.putDrivingData(randomTag, currentLocation, timestamp);
        log.console(ID + " is driving. Tag " + randomTag.getValue() + " (" + currentLocation.LATIDUDE + ", " + currentLocation.LONGITUDE + ") - " + timestamp);
        log.console(ID + " value of a toll station is " + toll);
    }

    public void reconciliation() {
        int c = 0;
        int bi;
        Element Di = ps.getOpeningKey();
        
        ArrayList<DrivingTuple> W = sp.getAllTags();
        PermutatedPackage Ui = new PermutatedPackage();
        
        log.console(ID + " is calculating cost...");
        for (DrivingTuple dr : W) {
            if(TAGS.contains(dr.tag)) {
                log.console(dr.tag.getValue().toString());
                c += dr.cost.convertToBigInteger().intValue();
            }   
        }
        log.console(ID + " calculated " + c);
        sp.putCostData(ID, c);
        
        // Send permutated data to service provider
        // Permute todo!
        
        // Opening Keys for Costs
        log.console(ID + " generates opening keys for costs");
        
        for (int x = 0; x <= i; x++) {
            for (int y = 0; y < W.size(); y++) {
                int index = x * y + y;
                DC.add(ps.getOpeningKey());
                log.console(ID + " tag opening key: " + DC.get(index).getValue());
            }
        }
        
        log.console(ID + " is permutating W and send the package to service provider");
        int m = 0;
        Ui.setId(ID);
        for (DrivingTuple dr : W) {
            Ui.addDrivintTuple(new DrivingTuple(hash.getHash(dr.tag, KEYS.get(i)), ps.commit(dr.cost, DC.get(m))));
            m++; 
        }
        sp.putPermutatedPackage(Ui);
        
        //bi = sp.getCheckMethod();
        bi = 0;
        log.console(ID + " bi is: " + Integer.toString(bi)); 
        
        // Vehicle sends to Service Provider
        if(bi == 0){
            sp.calculate0(ID, KEYS.get(i), DC);  
        }
        else if(bi == 1){
            sp.calculate1(ID, DV, Di);
        }
    }
}
