package com.rental;

import java.util.ArrayList;

public class RentalManager {
    private ArrayList<Vehicle> fleet = new ArrayList<>();

    public void addVehicle(Vehicle v) {
        fleet.add(v);
    }

    public void displayAllVehicles() {
        System.out.println("\n--- Available Fleet ---");
        for (Vehicle v : fleet) {
            System.out.println(v);
        }
    }

    public void searchByBrand(String brand) {
        System.out.println("\n--- Search Results for " + brand + " ---");
        for (Vehicle v : fleet) {
            if (v.getBrand().equalsIgnoreCase(brand)) {
                System.out.println(v);
            }
        }
    }

    public double calculateTotalRevenue(int rentalDays) {
        double total = 0;
        for (Vehicle v : fleet) {
            total += v.calculateRentalPrice(rentalDays);
        }
        return total;
    }


    public double rentVehicle(String vehicleId, int days) throws RentalException {

        if (days <= 0) {
            throw new RentalException("Error: Rental days must be greater than 0!");
        }

        Vehicle foundVehicle = null;
        for (Vehicle v : fleet) {
            if (v.getVehicleId().equalsIgnoreCase(vehicleId)) {
                foundVehicle = v;
                break;
            }
        }


        if (foundVehicle == null) {
            throw new RentalException("Error: Vehicle with ID " + vehicleId + " not found!");
        }

 
        if (!foundVehicle.isAvailable()) {
            throw new RentalException("Error: Vehicle " + vehicleId + " is already rented out!");
        }


        foundVehicle.setAvailable(false);
        return foundVehicle.calculateRentalPrice(days);
   
    public ArrayList<Vehicle> getFleet() {
        return fleet;
    }
    
}
