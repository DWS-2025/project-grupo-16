package com.projectdws.alquilercoches.models;

import java.util.ArrayList;
import java.util.List;

public class Dealership{

    private long ID = 0;
    private String name;
    private String location;
    private String address;
    private String tlf;
    private String description;
    private List <Car> cars = new ArrayList<>();

    public Dealership(){}

    public Dealership(String name, String location, String address, String tlf, String description) {
        this.name = name;
        this.location = location;
        this.address = address;
        this.tlf = tlf;
        this.description = description;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List <Car> getCars() {
        return cars;
    }

    public void setCars(List <Car> cars) {
        this.cars = cars;
    }

}
