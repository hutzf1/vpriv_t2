/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.ti.hutzf1.vprivt2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fh
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Log log = new Log();

        // Vehicle Variables
        int numberOfVehicles = 10;
        int n = 250; // number of new tags
        int s = 100; // number of new keys
        int maxToll = 50;
        int round = 1;
        int i = round-1; // round (i element of [1; s])
        
        // Generate Vehicles
        Vehicle v1 = new Vehicle();
        List<Vehicle> vehicles = new ArrayList<>();
        for (int x = 0; i < numberOfVehicles; i++) {
            Vehicle newVehicle = new Vehicle();
            vehicles.add(newVehicle);
            log.Console(newVehicle.GetId());
        }
        
        
        
        
        
        
        
        
    }
    
}
