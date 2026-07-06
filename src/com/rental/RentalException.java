package com.rental;

// This makes our class act like a professional error message
public class RentalException extends Exception {
    public RentalException(String message) {
        super(message);
    }
}