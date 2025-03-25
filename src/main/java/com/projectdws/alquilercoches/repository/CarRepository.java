package com.projectdws.alquilercoches.repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;

import com.projectdws.alquilercoches.models.Car;
import com.projectdws.alquilercoches.models.Dealership;

@Component
public class CarRepository {

    final private static AtomicLong nextId = new AtomicLong(1L);

    public static ConcurrentHashMap<Long, Car> cars = new ConcurrentHashMap<>();

    public List<Car> findAll() {
        return cars.values().stream().toList();
    }

    public Optional<Car> findById(Long id) {
        return Optional.ofNullable(cars.get(id));
    }

    public static void save(Car car) {
        Long id = car.getID();
        if (id == 0) {
            id = nextId.getAndIncrement();
            car.setID(id);
        }
        cars.put(id, car);

        for (Dealership dealership : car.getDealerships()) {
            dealership.getCars().add(car);
        }
    }

    public void update(Long id, Car updatedCar) {
        Car oldCar = cars.get(id);
        oldCar.setName(updatedCar.getName());
        oldCar.setImage(updatedCar.getImage());
        oldCar.setPrice(updatedCar.getPrice());
        oldCar.setDealerships(updatedCar.getDealerships());
    }

    public void delete(Long id) {
        cars.remove(id);
    }
}
