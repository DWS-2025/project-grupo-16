package com.projectdws.alquilercoches.dto;

import java.util.List;
//import java.util.stream.Collectors;

//import com.projectdws.alquilercoches.models.Car;

public class CarDTO {
    private String name;
    private double price;
    private String image;

    public CarDTO(String name, String image, int price) {
        this.name = name;
        this.image = image;
        this.price = price;
    }




    private List<DealershipDTO> dealerships;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public List<DealershipDTO> getDealerships() {
        return dealerships;
    }

    public void setDealerships(List<DealershipDTO> dealerships) {
        this.dealerships = dealerships;
    }


}