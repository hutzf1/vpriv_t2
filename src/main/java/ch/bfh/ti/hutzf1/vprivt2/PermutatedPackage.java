/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.ti.hutzf1.vprivt2;

import ch.bfh.unicrypt.math.algebra.general.interfaces.Element;
import java.util.ArrayList;

/**
 *
 * @author fh
 */
public class PermutatedPackage {
    String id;
    ArrayList<DrivingTuple> dr = new ArrayList<>();
    
    public void setId(String id) {
        this.id = id;
    }
    
    public void addDrivintTuple(DrivingTuple dr) {
        this.dr.add(dr);
    }

   
    
}
