package com.rental;

public class Truck extends Vehicle {
    private double cargoWeight;

    public Truck(String id, String brand, String model, double price, double cargoWeight) {
        super(id, brand, model, price);
        this.cargoWeight = cargoWeight;
    }

    @Override
    public double calculateRentalPrice(int days) {
        return (getBasePricePerDay() * days) + (20 * days);
    }
}