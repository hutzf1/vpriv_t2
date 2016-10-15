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
public class ReconciliationPackage {
    public String id;
    public int i;
    public ArrayList<CostTuple> U = new ArrayList<>();
    
    public void setId(String id) {
        this.id = id;
    }
    
    public void setRound(int i) {
        this.i = i;
    }
    
    public void addU(ArrayList<CostTuple> U) {
        this.U = U;
    }   
}
