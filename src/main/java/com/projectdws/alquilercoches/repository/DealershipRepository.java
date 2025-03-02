package com.projectdws.alquilercoches.repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;

import com.projectdws.alquilercoches.models.Dealership;

@Component

public class DealershipRepository {

    final private AtomicLong nextId = new AtomicLong(1L);
	final private ConcurrentHashMap<Long, Dealership> dealerships = new ConcurrentHashMap<>();

    public List<Dealership> findAll() {
        return dealerships.values().stream().toList();
    }

    public Optional<Dealership> findById(long id) {
        return Optional.ofNullable(dealerships.get(id));
    }

    public void save(Dealership dealership) {
        long id = dealership.getID();
        if (id == 0) {
            id = nextId.getAndIncrement();
            dealership.setID(id);
        }
        dealerships.put(id, dealership);
    }

}