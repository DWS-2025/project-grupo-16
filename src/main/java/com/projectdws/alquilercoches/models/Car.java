package com.projectdws.alquilercoches.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Car {
    private long ID = 0;
    private String name;
    private String image;
    private int price;
    private List <Dealership> dealerships = new ArrayList<>();
    private List <Comment> comments = new ArrayList<>();

    public Car() {}

    public Car(String name, String image, int price, List<Dealership> dealerships) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.dealerships = dealerships;
    }

    public Car(long ID, String name, String image, int price, List<Dealership> dealerships) {
        this.ID = ID;
        this.name = name;
        this.image = image;
        this.price = price;
        this.dealerships = dealerships;
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

    public List <Dealership> getDealerships() {
        return dealerships;
    }

    public void setDealerships(List <Dealership> dealerships) {
        this.dealerships = dealerships;
    }

    public List <Comment> getComments() {
        return comments;
    }

    public void setComments(List <Comment> comments) {
        this.comments = comments;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Car)) {
            return false;
        }
        Car car = (Car) o;
        return ID == car.ID;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ID);
    }
    
}