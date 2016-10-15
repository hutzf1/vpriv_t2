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

public class ServiceProvider {
    
    private ArrayList<RoundPackage> rp = new ArrayList<>();
    private ArrayList<DrivingTuple> W = new ArrayList<>();
    private ArrayList<CostTuple> costs = new ArrayList<>();
    
    public void putVehicleData(RoundPackage RI) {
        rp.add(RI);
    }
    
    public int putDrivingData(Element tag, Location location, Date timestamp) {
        Random rand = new Random();
        int toll = rand.nextInt(10);
        DrivingTuple dr = new DrivingTuple(tag, toll);
        W.add(dr);
        return toll;
    }    
    
    public ArrayList<DrivingTuple> getAllTags() {
        return W;
    }

    public void putCostData(String id, int c) {
        CostTuple ct = new CostTuple(id, c);
        costs.add(ct);
    }
    
    public int getCheckMethod() {
        Random rand = new Random();
        return rand.nextInt(2);
    }
    
    public int calculate0(String id, Element key, ArrayList<Element> dc) {
        return 0;
    }
    
    public int calculate1(String id, ArrayList<Element> dv, Element Di) {
        return 0;
    }
    
    
}
