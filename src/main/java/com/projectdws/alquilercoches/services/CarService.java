package com.projectdws.alquilercoches.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectdws.alquilercoches.models.Car;
import com.projectdws.alquilercoches.repository.CarRepository;

@Service
public class CarService {

    @Autowired
	private CarRepository carRepository;

	public List<Car> findAll() {
		return carRepository.findAll();
	}

	public Optional<Car> findById(long id) {
		return carRepository.findById(id);
	}

	public void save(Car car) {
		carRepository.save(car);		
	}

    public void update(Car oldCar, Car updatedCar) {
        carRepository.update(oldCar, updatedCar);
    }

    public void delete(Car car) {
        carRepository.delete(car);
    }
}
