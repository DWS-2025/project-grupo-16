package com.projectdws.alquilercoches.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.projectdws.alquilercoches.models.Car;
import com.projectdws.alquilercoches.models.Dealership;

@Component
public interface CarRepository extends JpaRepository<Car,Long> {    

}
