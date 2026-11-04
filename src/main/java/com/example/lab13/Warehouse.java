package com.example.lab13;

import com.example.lab13.exceptions.InvalidPriceException;
import com.example.lab13.exceptions.OutOfStockException;
import com.example.lab13.exceptions.ProductNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    private Map<String, Product> inventory = new HashMap<>();

    public void addProduct(Product product) {
        inventory.put(product.getName(), product);
    }

    public Product findProduct(String name) throws ProductNotFoundException {
        Product product = inventory.get(name);
        if (product == null) {
            throw new ProductNotFoundException("Товар не найден на складе", name);
        }
        return product;
    }

    public void purchaseProduct(String name, int amount) throws ProductNotFoundException, OutOfStockException {
        Product product = findProduct(name);
        product.reduceQuantity(amount);
        System.out.println("Успешная покупка: " + name + " (" + amount + " шт.). Остаток: " + product.getQuantity());
    }

    public void updateProductPrice(String name, double percent) throws ProductNotFoundException, InvalidPriceException {
        Product product = findProduct(name);
        product.increasePrice(percent);
        System.out.printf("Цена товара '%s' обновлена на %.1f%%. Новая цена: %.2f%n",
                name, percent, product.getPrice());
    }

    public void printInventory() {
        System.out.println("Текущее состояние склада:");
        for (Product product : inventory.values()) {
            System.out.println("  " + product);
        }
    }
}
