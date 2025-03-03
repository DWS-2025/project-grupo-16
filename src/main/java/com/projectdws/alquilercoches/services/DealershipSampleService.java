package com.projectdws.alquilercoches.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectdws.alquilercoches.models.Car;
import com.projectdws.alquilercoches.models.Comment;
import com.projectdws.alquilercoches.models.Dealership;
import com.projectdws.alquilercoches.models.User;

import jakarta.annotation.PostConstruct;

@Service
public class DealershipSampleService {

    @Autowired
    private DealershipService dealershipService;

    @Autowired
    private CarService carService;

    @PostConstruct
	public void init() {

		Dealership d1 = new Dealership("PrimeMotors", "Madrid", "C/ de Santa Engracia 10", "699 111 111", "Considerado el mejor concesionario de Madrid.");
		Dealership d2 = new Dealership("LuxeDrive", "Barcelona", "Avinguda diagonal", "699 222 222", "Uno de los concesionarios mas lujosos de Barcelona.");
        Dealership d3 = new Dealership("SmartWheels", "Valencia", "Avenida del Cid", "699 333 333", "Concesionario número 1 en cuanto a velocidad de gestión y fiabilidad.");

        dealershipService.save(d1);
        dealershipService.save(d2);
        dealershipService.save(d3);


        Car c1 = new Car("Audi_A4", "Audi_A4", 60000, d1);
        c1.getComments().add(new Comment(new User("Pepe", "pepe@gmail.com", "563784956"), 5, "Corre que flipas"));
        c1.getComments().add(new Comment(new User("Juan", "juan@gmail.com", "563784959"), 2, "Se me cala todo el rato"));
        carService.save(c1);


        Car c2 = new Car("BMW_M3", "BMW_M3", 50000, d1);
        carService.save(c2);
        Car c3 = new Car("Ford_Mustang", "Ford_Mustang", 120000, d1);
        carService.save(c3);
        Car c4 = new Car("Honda_Civic", "Honda_Civic", 15000, d2);
        carService.save(c4);
        
    }
}
