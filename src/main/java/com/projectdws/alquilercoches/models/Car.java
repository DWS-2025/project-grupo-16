package com.projectdws.alquilercoches.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Car {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID = 0;

    private String name;
    private String image;
    private int price;
    @ManyToMany
    private List <Dealership> dealerships = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carCommented")
    private List <Comment> comments = new ArrayList<>();

    public Car() {}

    public Car(String name, String image, int price, List<Dealership> dealerships) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.dealerships = dealerships;
    }

    public Car(Long ID, String name, String image, int price, List<Dealership> dealerships) {
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