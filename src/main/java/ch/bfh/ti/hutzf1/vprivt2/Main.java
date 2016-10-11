/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.ti.hutzf1.vprivt2;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;


/**
 *
 * @author fh
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ServiceProvider sp = new ServiceProvider();
        PedersenScheme ps = new PedersenScheme();
        Hash hash = new Hash();
        Log log = new Log();

        // Variables
        int numberOfVehicles = 10;
        int n = 20; // number of new tags
        int s = 10; // number of new keys
        int maxToll = 50;
        int round = 1;
        int i = round-1; // round (i element of [1; s])
        Random rand = new Random();
        
        // Generate Vehicles
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        for (int x = 0; i < numberOfVehicles; i++) {
            Vehicle newVehicle = new Vehicle(sp, ps, hash, log, n, s);
            vehicles.add(newVehicle);
            log.console(newVehicle.getId());
        }
        
        ////////////////////////
        // REGISTRATION PHASE //
        ////////////////////////
        
        // Registration is handled by each vehicle.
        // ENCRYPTION MISSING HERE!!!
        
        ///////////////////
        // DRIVING PHASE //
        ///////////////////
        
        log.console("Start Driving Phase");
        
        
        
        for (Vehicle vehicle : vehicles) {
            // Vehicle is driving, so how to do?
            Location currentLocation = new Location();
            Date timestamp = new Date();
            for(int y = 0; y < rand.nextInt(maxToll) + 1; y++) {
                //sp.putDrivingData(Vehicle., currentLocation, timestamp);
            }
        }
        
        log.console("End of Driving Phase");
        
        
        
        /*
        for(int y = 0; y < rand.nextInt(maxToll) + 1; y++) {
            int cost = rand.nextInt(5) + 1;
            //String tag = V[rand.nextInt(n)];
            //W.put(tag, cost);
            log.console("Vehicle drives through a Toll Station using Tag: " );// + tag);
        }
        */
        
        
        
        
        //////////////////////////
        // RECONCILIATION PHASE //
        //////////////////////////
        
        
        
        
        
        
        
    }
    
}
