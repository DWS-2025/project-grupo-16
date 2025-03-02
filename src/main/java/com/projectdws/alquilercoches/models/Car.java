package com.projectdws.alquilercoches.models;

import java.util.ArrayList;
import java.util.List;

public class Car {
    private String name;
    private String image;
    private int licensePlate;
    private int price;
    private Dealership dealership;
    private List <Comment> comments = new ArrayList<>();

    public Car() {}

    public Car(String name, String image, int licensePlate, int price) {
        this.name = name;
        this.image = image;
        this.licensePlate = licensePlate;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image){
        this.image = image;
    }

    public String getImage(){
        return image;
    }

    public int getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(int licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Dealership getDealership() {
        return dealership;
    }

    public void setDealership(Dealership dealership) {
        this.dealership = dealership;
    }

    public List <Comment> getComments() {
        return comments;
    }

    public void setComments(List <Comment> comments) {
        this.comments = comments;
    }
}