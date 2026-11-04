package com.example.lab13;

import com.example.lab13.exceptions.InvalidPriceException;
import com.example.lab13.exceptions.OutOfStockException;
import com.example.lab13.exceptions.ProductNotFoundException;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== ЛАБОРАТОРНАЯ РАБОТА №13 (Вариант 3: Товар) ===\n");

        Warehouse warehouse = new Warehouse();

        // 1. Создание товаров и проверка конструктора
        try {
            warehouse.addProduct(new Product("Ноутбук ASUS", 2500.0, 5, "Электроника"));
            warehouse.addProduct(new Product("Мышь Logitech", 80.0, 15, "Периферия"));
            warehouse.addProduct(new Product("Монитор LG", 700.0, 3, "Электроника"));

            System.out.println("Попытка создать товар с отрицательной ценой:");
            warehouse.addProduct(new Product("Сломанный товар", -50.0, 2, "Разное"));
        } catch (InvalidPriceException e) {
            System.out.println("[Ошибка цены]: " + e.getMessage() + " (указана цена: " + e.getInvalidPrice() + ")");
        } catch (IllegalArgumentException e) {
            System.out.println("[Ошибка аргумента]: " + e.getMessage());
        }

        System.out.println();
        warehouse.printInventory();
        System.out.println();

        // 2. Успешная покупка и ошибка нехватки товара на складе (OutOfStockException)
        try {
            warehouse.purchaseProduct("Ноутбук ASUS", 2);
            warehouse.purchaseProduct("Монитор LG", 10); // Вызовет OutOfStockException
        } catch (ProductNotFoundException e) {
            System.out.println("[Не найдено]: " + e.getMessage() + " -> " + e.getProductName());
        } catch (OutOfStockException e) {
            System.out.println("[Нет на складе]: " + e.getMessage() +
                    " (запрошено: " + e.getRequested() + ", доступно: " + e.getAvailable() + ")");
        }

        System.out.println();

        // 3. Попытка купить несуществующий товар (ProductNotFoundException)
        try {
            warehouse.purchaseProduct("Смартфон iPhone", 1);
        } catch (ProductNotFoundException e) {
            System.out.println("[Не найдено]: " + e.getMessage() + " ('" + e.getProductName() + "')");
        } catch (OutOfStockException e) {
            System.out.println("[Нет на складе]: " + e.getMessage());
        }

        System.out.println();

        // 4. Обновление цены (успешное и с ошибкой InvalidPriceException)
        try {
            warehouse.updateProductPrice("Мышь Logitech", 10.0);
            warehouse.updateProductPrice("Мышь Logitech", -120.0); // Снижение больше чем на 100%
        } catch (ProductNotFoundException e) {
            System.out.println("[Не найдено]: " + e.getMessage());
        } catch (InvalidPriceException e) {
            System.out.println("[Ошибка обновления цены]: " + e.getMessage() +
                    " (получившаяся цена: " + e.getInvalidPrice() + ")");
        } finally {
            System.out.println("\nБлок finally: операции со складом завершены.");
        }

        System.out.println();
        warehouse.printInventory();
    }
}
