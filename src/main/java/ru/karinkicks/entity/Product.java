package ru.karinkicks.entity;

public class Product {
    private int id;
    private int cost;
    private String title;

    public Product(int id, int cost, String title) {
        this.id = id;
        this.cost = cost;
        this.title = title;
    }

    @Override
    public String toString() {
        return  "id=" + id +
                ", cost=" + cost +
                ", title=" + title;
    }
}
