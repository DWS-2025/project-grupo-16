package main.java.com.classes;

import java.util.ArrayList;
import java.util.List;

public class user {

    private int ID_user = 00;
    private String name;
    private String email;
    private String tlf;
    private List <comment> comments = new ArrayList <> ();
    private List <car> cars = new ArrayList <> ();

    public user(){}

    public user(String name, String email, String tlf) {
        this.name = name;
        this.email = email;
        this.tlf = tlf;
    }

    public int getID() {
        return ID_user;
    }

    public void setID(int id) {
        this.id = ID_user;
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

    public List <comment> getComments() {
        return comments;
    }

    public void setComments(List <comment> comments) {
        this.comments = comments;
    }

    public List <car> getCars() {
        return cars;
    }

    public void setCars(List <car> cars) {
        this.cars = cars;
    }

    
}
