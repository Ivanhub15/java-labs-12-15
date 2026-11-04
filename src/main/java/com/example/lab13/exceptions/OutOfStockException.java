package com.example.lab13.exceptions;

public class OutOfStockException extends Exception {
    private int requested;
    private int available;

    public OutOfStockException(String message, int requested, int available) {
        super(message);
        this.requested = requested;
        this.available = available;
    }

    public int getRequested() {
        return requested;
    }

    public int getAvailable() {
        return available;
    }
}
