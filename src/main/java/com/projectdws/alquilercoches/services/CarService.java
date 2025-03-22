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
	public CarRepository carRepository;

	public List<Car> findAll() {
		return carRepository.findAll();
	}

	public Optional<Car> findById(long id) {
		return carRepository.findById(id);
	}

	public boolean save(Car car) {
		if(car.getPrice() > 0) {
			carRepository.save(car);
			return true;
		}
		return false;
	}

    public boolean update(long id, Car car) {
		if(car.getPrice() > 0) {
			carRepository.update(id, car);
			return true;
		}
		return false;
	}
	

    public void delete(long id) {
        carRepository.delete(id);
    }
}
