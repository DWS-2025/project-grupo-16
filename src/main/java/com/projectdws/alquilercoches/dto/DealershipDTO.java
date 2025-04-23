package com.projectdws.alquilercoches.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.projectdws.alquilercoches.models.Dealership;

public class DealershipDTO {

    private String name;
    private String location;
    private String address;
    private String tlf;
    private String description;
    private List<CarDTO> cars;

    public DealershipDTO(Dealership dealership) {
        this(dealership, true); // por defecto incluir coches
    }

    public DealershipDTO(Dealership dealership, boolean includeCars) {
        this.name = dealership.getName();
        this.location = dealership.getLocation();
        this.address = dealership.getAddress();
        this.tlf = dealership.getTlf();
        this.description = dealership.getDescription();

        if (includeCars) {
            this.cars = dealership.getCars()
                                  .stream()
                                  .map(car -> new CarDTO(car, false)) // para evitar bucle
                                  .collect(Collectors.toList());
        }
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
    public List<CarDTO> getCars() {
        return cars;
    }
    
    public void setCars(List<CarDTO> cars) {
        this.cars = cars;
    }

    

}