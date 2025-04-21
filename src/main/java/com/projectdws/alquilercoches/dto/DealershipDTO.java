package com.projectdws.alquilercoches.dto;

import com.projectdws.alquilercoches.models.Dealership;

public class DealershipDTO {

    
    private String name;
    private String location;
    private String address;
    private String tlf;
    private String description;








    public DealershipDTO(Dealership dealership) {
        this.name = dealership.getName();
        this.location = dealership.getLocation();
        this.address = dealership.getAddress();
        this.tlf = dealership.getTlf();
        this.description = dealership.getDescription();
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

    

}