package com.projectdws.alquilercoches.dto;

import java.util.List;
//import java.util.stream.Collectors;
import java.util.stream.Collectors;

import com.projectdws.alquilercoches.models.Car;

//import com.projectdws.alquilercoches.models.Car;

public class CarDTO {

    private String name;
    private double price;
    private String image;
    private List<DealershipDTO> dealerships;
    private List<CommentDTO> comments;

    public CarDTO(Car car, boolean includeDealershipsAndComments) {
        this.name = car.getName();
        this.image = car.getImage();
        this.price = car.getPrice();

    
        if (includeDealershipsAndComments) {
            this.dealerships = car.getDealerships()
                                  .stream()
                                  .map(d -> new DealershipDTO(d, false))
                                  .collect(Collectors.toList());
    
            this.comments = car.getComments()
                               .stream()
                               .map(CommentDTO::new)
                               .collect(Collectors.toList());
        }
    }
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
    
    public List<CommentDTO> getComments() {
        return comments;
    }
    
    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }


}