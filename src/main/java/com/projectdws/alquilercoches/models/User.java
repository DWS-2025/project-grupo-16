package com.projectdws.alquilercoches.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID = 0;
    
    private String name;
    private String email;
    private String tlf;
    @OneToMany
    private List <Comment> comments = new ArrayList <> ();
    @OneToMany
    private List <Car> cars = new ArrayList <> ();

    public User(){}

    public User(String name, String email, String tlf) {
        this.name = name;
        this.email = email;
        this.tlf = tlf;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public List <Comment> getComments() {
        return comments;
    }

    public void setComments(List <Comment> comments) {
        this.comments = comments;
    }

    public List <Car> getCars() {
        return cars;
    }

    public void setCars(List <Car> cars) {
        this.cars = cars;
    }

    
}
