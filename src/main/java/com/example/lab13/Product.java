package com.example.lab13;

import com.example.lab13.exceptions.InvalidPriceException;
import com.example.lab13.exceptions.OutOfStockException;

public class Product {
    private String name;
    private double price;
    private int quantity;
    private String category;

    public Product(String name, double price, int quantity, String category) throws InvalidPriceException {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Название товара не может быть пустым");
        }
        if (price <= 0) {
            throw new InvalidPriceException("Цена должна быть больше 0", price);
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Количество товара не может быть отрицательным: " + quantity);
        }
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    public void reduceQuantity(int amount) throws OutOfStockException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Количество для списания должно быть положительным");
        }
        if (amount > quantity) {
            throw new OutOfStockException("Недостаточно товара '" + name + "' на складе", amount, quantity);
        }
        this.quantity -= amount;
    }

    public void increasePrice(double percent) throws InvalidPriceException {
        if (percent <= -100) {
            throw new InvalidPriceException("Процент изменения цены делает цену <= 0", price * (1 + percent / 100.0));
        }
        double newPrice = this.price + (this.price * percent / 100.0);
        if (newPrice <= 0) {
            throw new InvalidPriceException("Новая цена должна быть больше 0", newPrice);
        }
        this.price = newPrice;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return String.format("Product{name='%s', price=%.2f, quantity=%d, category='%s'}",
                name, price, quantity, category);
    }
}
