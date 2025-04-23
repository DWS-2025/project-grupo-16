package com.projectdws.alquilercoches.controller;

import java.util.List;
import java.util.stream.Collectors;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectdws.alquilercoches.dto.CarDTO;
import com.projectdws.alquilercoches.dto.DealershipDTO;
import com.projectdws.alquilercoches.services.CarService;
import com.projectdws.alquilercoches.services.DealershipService;

//import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api")
public class APIRestController {

    private final CarService carService;
    private final DealershipService dealershipService;

    // @Autowired
    public APIRestController(CarService carService, DealershipService dealershipService) {
        this.carService = carService;
        this.dealershipService = dealershipService;
    }

    @GetMapping("/cars")
    public List<CarDTO> getAllCars() {
        return carService.findAll()
                         .stream()
                         .map(car -> new CarDTO(car, true))                         
                         .collect(Collectors.toList());
    }

    @GetMapping("/dealerships")
    public List<DealershipDTO> getAllDealerships() {
        return dealershipService.findAll()
            .stream()
            .map(dealership -> new DealershipDTO(dealership))
            .collect(Collectors.toList());
    }
}

