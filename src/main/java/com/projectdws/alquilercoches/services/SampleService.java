package com.projectdws.alquilercoches.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectdws.alquilercoches.models.Car;
import com.projectdws.alquilercoches.models.Comment;
import com.projectdws.alquilercoches.models.Dealership;
import com.projectdws.alquilercoches.models.User;

import jakarta.annotation.PostConstruct;

@Service
public class SampleService {

    private final CommentService commentService;

    private final UserService userService;

    @Autowired
    private DealershipService dealershipService;

    @Autowired
    private CarService carService;

    SampleService(UserService userService, CommentService commentService) {
        this.userService = userService;
        this.commentService = commentService;
    }

    @PostConstruct
	public void init() {

		Dealership d1 = new Dealership("PrimeMotors", "Madrid", "C/ de Santa Engracia 10", "699 111 111", "Considerado el mejor concesionario de Madrid.");
        dealershipService.save(d1);

        Dealership d2 = new Dealership("LuxeDrive", "Barcelona", "Avinguda diagonal", "699 222 222", "Uno de los concesionarios mas lujosos de Barcelona.");
        dealershipService.save(d2);

        Dealership d3 = new Dealership("SmartWheels", "Valencia", "Avenida del Cid", "699 333 333", "Concesionario número 1 en cuanto a velocidad de gestión y fiabilidad.");
        dealershipService.save(d3);

        List <Dealership> dps1 = new ArrayList<>();
        dps1.add(d1);
        dps1.add(d2);

        List <Dealership> dps2 = new ArrayList<>();
        dps2.add(d2);
        dps2.add(d3);

        User u1 = new User("Pepe", "pepe@gmail.com", "563784956");
        userService.save(u1);

        User u2 = new User("Juan", "juan@gmail.com", "563784959");
        userService.save(u2);

        Comment com1 = new Comment(u1, 5, "Corre que flipas");
        Comment com2 = new Comment(u2, 2, "Se me cala todo el rato");


        Car c1 = new Car("Audi_A4", "/images/Audi_A4.jpg", 60000, dps1);
        c1.getComments().add(com1);
        c1.getComments().add(com2);
        carService.save(c1);

        Car c2 = new Car("BMW_M3", "/images/BMW_M3.jpg", 50000, dps1);
        carService.save(c2);

        Car c3 = new Car("Ford_Mustang", "/images/Ford_Mustang.jpg", 120000, dps1);
        carService.save(c3);
        
        Car c4 = new Car("Honda_Civic", "/images/Honda_Civic.jpg", 15000, dps2);
        carService.save(c4);
        
    }
}
