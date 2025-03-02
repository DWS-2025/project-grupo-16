package com.projectdws.alquilercoches.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectdws.alquilercoches.models.Dealership;

import jakarta.annotation.PostConstruct;

@Service
public class DealershipSampleService {

    @Autowired
    private DealershipService dealershipService;

    @PostConstruct
	public void init() {

		Dealership d1 = new Dealership("PrimeMotors", "Madrid", "C/ de Santa Engracia 10", "699 111 111", "Considerado el mejor concesionario de Madrid.");
		Dealership d2 = new Dealership("LuxeDrive", "Barcelona", "Avinguda diagonal", "699 222 222", "Uno de los concesionarios mas lujosos de Barcelona.");
        Dealership d3 = new Dealership("SmartWheels", "Valencia", "Avenida del Cid", "699 333 333", "Concesionario número 1 en cuanto a velocidad de gestión y fiabilidad.");

        dealershipService.save(d1);
        dealershipService.save(d2);
        dealershipService.save(d3);

    }
}
