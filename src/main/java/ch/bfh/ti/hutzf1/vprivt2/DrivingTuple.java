/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.ti.hutzf1.vprivt2;

import ch.bfh.unicrypt.math.algebra.general.interfaces.Element;

/**
 *
 * @author fh
 */

public class DrivingTuple {
    public Element tag;
    public Element cost;
    
    public DrivingTuple(Element tag, Element cost) {
        this.tag = tag;
        this.cost = cost;  
    }
}
