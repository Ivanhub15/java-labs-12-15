package com.example.lab12.task1;

public class Dog extends Animal {
    private String breed;

    public Dog(String name, int id, int age, String breed) {
        super(name, id, age);
        this.breed = breed;
    }

    public String getBreed() {
        return breed;
    }

    @Override
    public String toString() {
        return "Dog{" + super.toString() + ", breed='" + breed + "'}";
    }
}
