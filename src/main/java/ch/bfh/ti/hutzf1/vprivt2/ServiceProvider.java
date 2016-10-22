/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.ti.hutzf1.vprivt2;

import ch.bfh.unicrypt.math.algebra.general.classes.BooleanElement;
import ch.bfh.unicrypt.math.algebra.general.interfaces.Element;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author fh
 */

public class ServiceProvider {
    
    private final PedersenScheme ps;
    private final Log log;
    private final Hash hash;
    private final ArrayList<RoundPackage> RP = new ArrayList<>();
    private final ArrayList<DrivingTuple> W = new ArrayList<>();
    private final ArrayList<CostTuple> COSTS = new ArrayList<>();
    private final ArrayList<PermutatedPackage> PP = new ArrayList<>();
    
    public ServiceProvider(PedersenScheme ps, Log log, Hash hash){
        this.ps = ps;
        this.log = log;
        this.hash = hash;
    }
    
    public void putVehicleData(RoundPackage RI) {
        RP.add(RI);
    }
    
    public int putDrivingData(Element tag, Location location, Date timestamp) {
        Random rand = new Random();
        int toll = rand.nextInt(10);
        DrivingTuple dr = new DrivingTuple(tag, ps.getElement(toll));
        W.add(dr);
        return toll;
    }    
    
    public ArrayList<DrivingTuple> getAllTags() {
        return W;
    }

    public void putCostData(String id, int c) {
        CostTuple ct = new CostTuple(id, c);
        COSTS.add(ct);
    }
    
    public void putPermutatedPackage(PermutatedPackage pp) {
        this.PP.add(pp);
    }
    
    public int getCheckMethod() {
        Random rand = new Random();
        return rand.nextInt(2);
    }
    
    public int calculate0(String id, Element key, ArrayList<Element> dc) throws IOException {
        log.file("-----------------------------------");
        log.file("BI = 0, Service Provider calculates");
        log.file("-----------------------------------");
        
        PermutatedPackage vehiclesPackage = null;
        
        for (PermutatedPackage pp : PP) {
            if(pp.id.equals(id)) {
                vehiclesPackage = pp;
                log.console("Package: " + vehiclesPackage.id);
            }
        }
        
        /*RoundPackage vehiclesRound = null;
        
        for (RoundPackage rp : RP) {
            if(rp.id == id) {
                vehiclesRound = rp;
                log.console("Package: " + vehiclesPackage.id);
            }
        }*/
         
        int sum = 0;
        
        for (DrivingTuple dt : W) {
            int m = 0;
            for (DrivingTuple vehicleDt : vehiclesPackage.dr) {
                if(ps.commit(dt.cost, dc.get(m)).equals(vehicleDt.cost)) {
                if(hash.getHash(dt.tag, key).equals(vehicleDt.tag)) {
                    //if(ps.commit(dt.cost, dc.get(m)).equals(vehicleDt.cost)) {
                        log.file(ps.commit(dt.cost, dc.get(m)).toString());
                        log.file(vehicleDt.cost.toString());
                        sum = sum + dt.cost.convertToBigInteger().intValue();
                    }
                }
                m++;
            }
        }
        
        // Ich erhalte
        // id vom Fahrzeug
        // dki vom Fahrzeug
        // dci1 - dcim vom Fahrzeug
        //
        // Ich habe
        // Ui mit fki(w), c(dci)
        
        log.file("-----------------------------------");
        log.file("-----------------------------------");
        return sum;
    }
    
    public int calculate1(String id, ArrayList<Element> dv, Element Di) throws IOException {
        log.file(id + " calculating by service provider");
        log.file("-----------------------------------");
        log.file("-----------------------------------");
        
        // SP has: License Plate (id), round (i), commitment of hased tags, all Tags of Driving Phase (unhashed, uncommited)
        // SP recieves: License Plate (id), Opening Keys for Tags (DV), Opening key for costs (Di), All tags of Driving Phase (permuted and commitment of)
        // for bi = 1, SP needs:
        
        PermutatedPackage thispp = null;
        RoundPackage thisrp = null;
        
        for (PermutatedPackage pp : PP){
            if(pp.id.equals(id)) {
                thispp = pp;
                log.file("pp: " + thispp.id);
            }
        }
        
        for (RoundPackage rp : RP){
            if(rp.id.equals(id)) {
                thisrp = rp;
                log.file("rp: " + thisrp.id);
            }
        }
        
        int sum = 0;
        
        for (DrivingTuple d1 : thispp.dr) {
            int i = 0;
            
            for (Element e : thisrp.hashes) {
                if(e.equals(ps.commit(d1.tag, dv.get(i)))) {
                    log.file(d1.cost.toString());
                    for(DrivingTuple d2 : W) {
                        if(e.equals(ps.commit(d2.tag, dv.get(i)))){
                            sum = sum + d2.cost.convertToBigInteger().intValue();
                        }
                    }
                }
            }
            i++;
        }

        
        
        log.file("-----------------------------------");
        log.file("-----------------------------------");
        
        return sum;
    }
    
    
}
