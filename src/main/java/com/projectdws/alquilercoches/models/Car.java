package com.projectdws.alquilercoches.models;

import java.util.ArrayList;
import java.util.List;

public class Car {
    private long ID = 0;
    private String name;
    private String image;
    private int price;
    private Dealership dealership;
    private List <Comment> comments = new ArrayList<>();

    public Car() {}

    public Car(String name, String image, int price, Dealership dealership) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.dealership = dealership;
    }

    public Car(long ID, String name, String image, int price, Dealership dealership) {
        this.ID = ID;
        this.name = name;
        this.image = image;
        this.price = price;
        this.dealership = dealership;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
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