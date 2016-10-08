/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.ti.hutzf1.vprivt2;

/**
 *
 * @author fh
 */
public class Location {
    
    public final int LATIDUDE = randomLocation();
    public final int LONGITUDE = randomLocation();
    
    private int randomLocation() {
        return 10;
    }
}
