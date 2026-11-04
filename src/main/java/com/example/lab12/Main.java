package com.example.lab12;

import com.example.lab12.task1.Animal;
import com.example.lab12.task1.Bird;
import com.example.lab12.task1.Cat;
import com.example.lab12.task1.Dog;
import com.example.lab12.task2.Product;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== ЛАБОРАТОРНАЯ РАБОТА №12 (Вариант 3) ===\n");

        runTask1();
        System.out.println("\n--------------------------------------------\n");
        runTask2();
    }

    private static void runTask1() {
        System.out.println("--- Задание 1: Животные (Map<String, Animal>) ---");
        Map<String, Animal> animals = new LinkedHashMap<>();

        // 3 Dog
        Dog d1 = new Dog("Рекс", 1, 5, "Немецкая овчарка");
        Dog d2 = new Dog("Шарик", 2, 3, "Дворняга");
        Dog d3 = new Dog("Барон", 3, 4, "Лабрадор");

        // 3 Cat
        Cat c1 = new Cat("Мурзик", 4, 2, "Рыжий");
        Cat c2 = new Cat("Барсик", 5, 6, "Черный");
        Cat c3 = new Cat("Снежок", 6, 1, "Белый");

        // 2 Bird
        Bird b1 = new Bird("Кеша", 7, 2, 0.25);
        Bird b2 = new Bird("Орел", 8, 7, 2.10);

        animals.put(d1.getName(), d1);
        animals.put(d2.getName(), d2);
        animals.put(d3.getName(), d3);
        animals.put(c1.getName(), c1);
        animals.put(c2.getName(), c2);
        animals.put(c3.getName(), c3);
        animals.put(b1.getName(), b1);
        animals.put(b2.getName(), b2);

        for (Map.Entry<String, Animal> entry : animals.entrySet()) {
            System.out.println("Ключ (Имя): " + entry.getKey() + " -> Значение: " + entry.getValue());
        }
    }

    private static void runTask2() {
        System.out.println("--- Задание 2: Товары (List<Product>) ---");
        List<Product> products = new ArrayList<>();

        products.add(new Product(101, "Смартфон", "Электроника", 3, Arrays.asList(5, 4, 5, 4)));
        products.add(new Product(102, "Наушники", "Электроника", 2, Arrays.asList(2, 3, 2, 1)));
        products.add(new Product(103, "Ноутбук", "Электроника", 4, Arrays.asList(5, 5, 4, 5)));
        products.add(new Product(104, "Клавиатура", "Аксессуары", 3, Arrays.asList(4, 4, 4, 4)));
        products.add(new Product(105, "Мышь", "Аксессуары", 3, Arrays.asList(5, 4, 5, 4)));
        products.add(new Product(106, "Кабель USB", "Аксессуары", 1, Arrays.asList(1, 2, 2)));

        System.out.println("Исходный список товаров:");
        products.forEach(System.out::println);

        // 1. Удалить товары со средним рейтингом < 3
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product p = iterator.next();
            if (p.getAverageRating() < 3.0) {
                iterator.remove();
            }
        }
        System.out.println("\nПосле удаления товаров со средним рейтингом < 3:");
        products.forEach(System.out::println);

        // 2. Повысить рейтинг товаров со средним рейтингом >= 4
        for (Product p : products) {
            if (p.getAverageRating() >= 4.0) {
                p.setRating(p.getRating() + 1);
            }
        }
        System.out.println("\nПосле повышения рейтинга товаров со средним рейтингом >= 4:");
        products.forEach(System.out::println);

        // 3. printProducts(List<Product> products, int rating) - печать товаров с рейтингом
        int targetRating = 4;
        System.out.println("\nПечать товаров с рейтингом " + targetRating + ":");
        printProducts(products, targetRating);

        // 4. Упорядочить все товары по убыванию среднего рейтинга.
        // При совпадении - в алфавитном порядке по названию. Вывести ID, название и средний рейтинг.
        products.sort(Comparator.comparingDouble(Product::getAverageRating).reversed()
                .thenComparing(Product::getName));

        System.out.println("\nОтсортированный список товаров (ID, название, средний рейтинг):");
        for (Product p : products) {
            System.out.printf("ID: %d | Название: %-12s | Средний рейтинг: %.2f%n",
                    p.getId(), p.getName(), p.getAverageRating());
        }
    }

    public static void printProducts(List<Product> products, int rating) {
        for (Product p : products) {
            if (p.getRating() == rating) {
                System.out.println(p);
            }
        }
    }
}
