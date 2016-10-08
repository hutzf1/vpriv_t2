/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.ti.hutzf1.vprivt2;

import java.util.ArrayList;

/**
 *
 * @author fh
 */
public class RoundPackage {
    public final String ID;
    public final int I;
    public final String KEY;
    public final ArrayList<String> HASHES;
    
    public RoundPackage(String id, int i, String key, ArrayList<String> hashes) {
        ID = id;
        I = i;
        KEY = key;
        HASHES = hashes;
    }
}
