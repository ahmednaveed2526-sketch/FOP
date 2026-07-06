package com.rental;

public class Motorcycle extends Vehicle {
    public Motorcycle(String id, String brand, String model, double price) {
        super(id, brand, model, price);
    }

    @Override
    public double calculateRentalPrice(int days) {
        double total = getBasePricePerDay() * days;
        if (days >= 3) total *= 0.90;
        return total;
    }
}