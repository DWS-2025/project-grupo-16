package com.projectdws.alquilercoches.repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;

import com.projectdws.alquilercoches.models.Car;


@Component
public class CarRepository {
     final private AtomicLong nextId = new AtomicLong(1L);
	final private ConcurrentHashMap<Long, Car> cars = new ConcurrentHashMap<>();

    public List<Car> findAll() {
        return cars.values().stream().toList();
    }

    public Optional<Car> findById(long id) {
        return Optional.ofNullable(cars.get(id));
    }

    public void save(Car car) {
        long id = car.getID();
        if (id == 0) {
            id = nextId.getAndIncrement();
            car.setID(id);
        }
        cars.put(id, car);
    }

    public void update(long id, Car updatedCar) {
        Car oldCar = cars.get(id);
        oldCar.setName(updatedCar.getName());
        oldCar.setImage(updatedCar.getImage());
        oldCar.setPrice(updatedCar.getPrice());
        oldCar.setDealership(updatedCar.getDealership());
    }

    public void delete(long id) {
        cars.remove(id);
    }
}
