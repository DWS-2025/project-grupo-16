package com.projectdws.alquilercoches.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectdws.alquilercoches.models.Car;
import com.projectdws.alquilercoches.models.Comment;
import com.projectdws.alquilercoches.models.Dealership;
import com.projectdws.alquilercoches.models.User;
import com.projectdws.alquilercoches.repository.CarRepository;
import com.projectdws.alquilercoches.repository.CommentRepository;
import com.projectdws.alquilercoches.repository.DealershipRepository;
import com.projectdws.alquilercoches.repository.UserRepository;

import jakarta.annotation.PostConstruct;

@Service
public class SampleDataService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DealershipRepository dealershipRepository;

    @Autowired
    private CarRepository carRepository;

    @PostConstruct
	public void init() {

		Dealership d1 = new Dealership("PrimeMotors", "Madrid", "C/ de Santa Engracia 10", "699 111 111", "Considerado el mejor concesionario de Madrid.");
        Dealership d2 = new Dealership("LuxeDrive", "Barcelona", "Avinguda diagonal", "699 222 222", "Uno de los concesionarios mas lujosos de Barcelona.");
        Dealership d3 = new Dealership("SmartWheels", "Valencia", "Avenida del Cid", "699 333 333", "Concesionario número 1 en cuanto a velocidad de gestión y fiabilidad.");

        List <Dealership> dps1 = new ArrayList<>();
        List <Dealership> dps2 = new ArrayList<>();

        User u1 = new User("Pepe", "pepe@gmail.com", "563784956");
        User u2 = new User("Juan", "juan@gmail.com", "563784959");
        
        Car c1 = new Car("Audi_A4", "/images/Audi_A4.jpg", 60000, dps1);
        Car c2 = new Car("BMW_M3", "/images/BMW_M3.jpg", 50000, dps1);
        Car c3 = new Car("Ford_Mustang", "/images/Ford_Mustang.jpg", 120000, dps1);
        Car c4 = new Car("Honda_Civic", "/images/Honda_Civic.jpg", 15000, dps2);
        
       //Comment com1 = new Comment(u1, 5, "Corre que flipas", c1);
       //Comment com2 = new Comment(u2, 2, "Se me cala todo el rato", c1);

        dealershipRepository.save(d1);
        dealershipRepository.save(d2);
        dealershipRepository.save(d3);

        dps1.add(d1);
        dps1.add(d2);
        dps2.add(d2);
        dps2.add(d3);
        
        userRepository.save(u1);
        userRepository.save(u2);

        carRepository.save(c1);
        carRepository.save(c2);
        carRepository.save(c3);
        carRepository.save(c4);

       // commentRepository.save(com1);
       // commentRepository.save(com2);   
    }
}
