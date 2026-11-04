package com.example.lab12.task1;

public abstract class Animal {
    private String name;
    private int id;
    private int age;

    public Animal(String name, int id, int age) {
        this.name = name;
        this.id = id;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "id=" + id + ", name='" + name + "', age=" + age;
    }
}
