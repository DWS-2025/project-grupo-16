package com.projectdws.alquilercoches.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectdws.alquilercoches.dto.CarDTO;
import com.projectdws.alquilercoches.services.CarService;

@RestController
@RequestMapping("/api")

public class APIRestController{


    @Autowired
    private final CarService carService;

    public APIRestController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public List<CarDTO> getAllCars() {
        return carService.findAll()
                         .stream()
                         .map(car -> new CarDTO(car.getName(), car.getImage(), car.getPrice()))
                         .collect(Collectors.toList());
    }
}

