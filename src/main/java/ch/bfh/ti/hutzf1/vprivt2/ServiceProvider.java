/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.ti.hutzf1.vprivt2;

import ch.bfh.unicrypt.math.algebra.general.interfaces.Element;
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
    private final ArrayList<RoundPackage> RP = new ArrayList<>();
    private final ArrayList<DrivingTuple> W = new ArrayList<>();
    private final ArrayList<CostTuple> COSTS = new ArrayList<>();
    private final ArrayList<PermutatedPackage> PP = new ArrayList<>();
    
    public ServiceProvider(PedersenScheme ps, Log log){
        this.ps = ps;
        this.log = log;
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
    
    public int calculate0(String id, Element key, ArrayList<Element> dc) {
        log.console("-----------------------------------");
        log.console("BI = 0, Service Provider calculates");
        log.console("-----------------------------------");
        for (DrivingTuple dt : W){
            Element tag = ps.commit(dt.tag, key);
            
        }
        
        /*String ki = ps. Decommit(DK[i],Ri.get(1));
        int b = 2;
        for (Map.Entry<String, Integer> entry : W.entrySet())
        {
            String value = entry.getValue().toString();
            int c = Integer.parseInt(Decommit(Bi.get(b), value));

            Log(Integer.toString(c));

            String key = entry.getKey();

            if (Ui.containsKey(F(ki,key)) && Ui.get(F(ki,key)).equals(value)) {
                Csp += c;
            }

            b++;
        }*/
        log.console("-----------------------------------");
        log.console("-----------------------------------");
        return 0;
    }
    
    public int calculate1(String id, ArrayList<Element> dv, Element Di) {
        log.console(id + " calculating by service provider");
        for (RoundPackage r : RP){
            log.console("r: " + r.id);
            if(r.id == id) {
                for (Element e : dv) {
                    // TODO
                    log.console(e.convertToString());
                }
            }
        }
        
        
        /*for (Element e : dv) {
            
            String key = Decommit(Bi.get(y-1),Ri.get(y));
            Log(key);
            String value = Ui.get(key);
            if(value != null){
                Csp += Integer.parseInt(value);
            }
        }*/
        return 0;
    }
    
    
}
