package com.example.lab12.task2;

import java.util.List;

public class Product {
    private int id;
    private String name;
    private String category;
    private int rating;
    private List<Integer> customerReviews;

    public Product(int id, String name, String category, int rating, List<Integer> customerReviews) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.rating = rating;
        this.customerReviews = customerReviews;
    }

    public double getAverageRating() {
        if (customerReviews == null || customerReviews.isEmpty()) {
            return 0.0;
        }
        int sum = 0;
        for (int review : customerReviews) {
            sum += review;
        }
        return (double) sum / customerReviews.size();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public List<Integer> getCustomerReviews() {
        return customerReviews;
    }

    @Override
    public String toString() {
        return String.format("Product{id=%d, name='%s', category='%s', rating=%d, avgReview=%.2f}",
                id, name, category, rating, getAverageRating());
    }
}
