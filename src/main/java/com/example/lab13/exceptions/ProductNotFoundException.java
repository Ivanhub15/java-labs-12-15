package com.example.lab13.exceptions;

public class ProductNotFoundException extends Exception {
    private String productName;

    public ProductNotFoundException(String message, String productName) {
        super(message);
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }
}
