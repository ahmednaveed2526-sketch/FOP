package com.rental;

public class Car extends Vehicle {
    public Car(String id, String brand, String model, double price) {
        super(id, brand, model, price);
    }

    @Override
    public double calculateRentalPrice(int days) {
        return (getBasePricePerDay() * days) * 1.10; 
    }
}