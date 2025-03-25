package com.projectdws.alquilercoches.models;

import java.util.HashSet;
import java.util.Set;

public class Dealership{

    private long ID = 0;
    private String name;
    private String location;
    private String address;
    private String tlf;
    private String description;
    private Set <Car> cars = new HashSet<>();

    public Dealership(){}

    public Dealership(String name, String location, String address, String tlf, String description) {
        this.name = name;
        this.location = location;
        this.address = address;
        this.tlf = tlf;
        this.description = description;
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

    public Set <Car> getCars() {
        return cars;
    }

    public void setCars(Set <Car> cars) {
        this.cars = cars;
    }

}
