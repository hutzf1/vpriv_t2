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
        int numberOfVehicles = 2;
        int n = 5; // number of new tags
        int s = 2; // number of new keys
        int maxToll = n;
        int round = 1;
        int i = round-1; // round (i element of [1; s])
        Random rand = new Random();
        
        log.console("Start Registration Phase");
        
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
        
        log.console("End Registration Phase");
        
        ///////////////////
        // DRIVING PHASE //
        ///////////////////
        
        log.console("Start Driving Phase");
        
        for (Vehicle vehicle : vehicles) {
            // Vehicle is driving, so how to do?
            for(int y = 0; y < rand.nextInt(maxToll) + 1; y++) {
                Location currentLocation = new Location();
                Date timestamp = new Date();
                Element randomTag = vehicle.getRandomTag();
                int toll = sp.putDrivingData(randomTag, currentLocation, timestamp);
                log.console(vehicle.getId() + " is driving. Tag " + randomTag.getValue() + " (" + currentLocation.LATIDUDE + ", " + currentLocation.LONGITUDE + ") - " + timestamp);
                log.console(vehicle.getId() + " value of a toll station is " + toll);
            }
        }
        
        log.console("End of Driving Phase");

        //////////////////////////
        // RECONCILIATION PHASE //
        //////////////////////////
        
        log.console("Start Reconciliation Phase");
        
        for (Vehicle vehicle : vehicles) {
            log.console(vehicle.getId() + " is calculating cost...");
            int c = vehicle.calcCost(sp.getAllTags(), log);
            log.console(vehicle.getId() + " calculated " + c);
            //sp.putCostData(vehicle.getId(), c);
        }        
    }
    
}
