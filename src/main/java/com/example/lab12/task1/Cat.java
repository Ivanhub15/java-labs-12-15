package com.example.lab12.task1;

public class Cat extends Animal {
    private String color;

    public Cat(String name, int id, int age, String color) {
        super(name, id, age);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Cat{" + super.toString() + ", color='" + color + "'}";
    }
}
