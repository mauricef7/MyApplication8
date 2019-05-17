package com.example.myapplication8.Database;

public class Product {
    private long id;
    private String category;
    private String name;
    private double price;

    public Product (final String category){
        this(category, null);
    }

    public Product (final String category, final String name, final double price){
        this.category = category;
        this.name = name;
        this.price = price;


    }
    public String getName() {return name;}
    public void setName ()  (final String name) {this.name = name;}

    public String getCategory() {return category;}
    public void setCategory ()  (final String category) {this.category = category;}

    public double getPrice() {return price;}
    public void setPrice ()  (final double price) {this.price = price;}

    public long getId () {
        return id;
    }
    public void setId (final long id){
        this.id = id;
    }
}
