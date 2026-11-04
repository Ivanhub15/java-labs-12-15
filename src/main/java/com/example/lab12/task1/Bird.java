package com.example.lab12.task1;

public class Bird extends Animal {
    private double wingspan;

    public Bird(String name, int id, int age, double wingspan) {
        super(name, id, age);
        this.wingspan = wingspan;
    }

    public double getWingspan() {
        return wingspan;
    }

    @Override
    public String toString() {
        return "Bird{" + super.toString() + ", wingspan=" + wingspan + "}";
    }
}
