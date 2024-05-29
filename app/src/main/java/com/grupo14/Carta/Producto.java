package com.grupo14.Carta;

public class Producto {
    private String title;
    private String description;
    private double price;
    private String imageResourceName;

    public Producto(String title, String description, double price, String imageResourceName) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.imageResourceName = imageResourceName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageResourceName() {
        return imageResourceName;
    }

    public void setImageResourceName(String imageResourceName) {
        this.imageResourceName = imageResourceName;
    }
}