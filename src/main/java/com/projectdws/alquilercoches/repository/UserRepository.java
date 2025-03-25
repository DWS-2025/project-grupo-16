package com.projectdws.alquilercoches.repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;

import com.projectdws.alquilercoches.models.User;

@Component
public class UserRepository {

    private AtomicLong nextId = new AtomicLong(1L);

    private ConcurrentHashMap<Long, User> users = new ConcurrentHashMap<>();

    public List<User> findAll() {
        return users.values().stream().toList();
    }

    public Optional<User> findById(Long id) {
        return Optional.ofNullable(users.get(id));
    }

    public void save(User user) {
        Long id = user.getID();
        if (id == 0) {
            id = nextId.getAndIncrement();
            user.setID(id);
        }
        users.put(id, user);
    }

    public void update(Long id, User updatedUser) {
        User oldUser = users.get(id);
        oldUser.setName(updatedUser.getName());
        oldUser.setEmail(updatedUser.getEmail());
        oldUser.setTlf(updatedUser.getTlf());
    }

    public void delete(Long id) {
        User user = users.get(id);
        if (user != null) {
            user.getCars().clear();
            user.getComments().clear();
            users.remove(id);
        }
    }
}