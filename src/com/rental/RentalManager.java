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

    // --- NEW: Exception Handling Method ---
    public double rentVehicle(String vehicleId, int days) throws RentalException {
        // 1. Check for invalid days
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

        // 2. Check if vehicle exists
        if (foundVehicle == null) {
            throw new RentalException("Error: Vehicle with ID " + vehicleId + " not found!");
        }

        // 3. Check if vehicle is already rented
        if (!foundVehicle.isAvailable()) {
            throw new RentalException("Error: Vehicle " + vehicleId + " is already rented out!");
        }

        // If all checks pass, rent the vehicle
        foundVehicle.setAvailable(false);
        return foundVehicle.calculateRentalPrice(days);
    }
        // Add this so the GUI can read the fleet list
    public ArrayList<Vehicle> getFleet() {
        return fleet;
    }
    
}