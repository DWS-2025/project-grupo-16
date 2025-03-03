package com.projectdws.alquilercoches.repository;

import org.springframework.stereotype.Component;
import com.projectdws.alquilercoches.models.User;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class UserRepository {

    private AtomicLong nextId = new AtomicLong(1L); 

    private ConcurrentHashMap<Long, User> userMap = new ConcurrentHashMap<>();

    public List<User> findAll() {
        return userMap.values().stream().toList();
    }

    public void save(User user) {
        long id = user.getID();
        if (id == 0) {
            id = nextId.getAndIncrement(); 
            user.setID(id);
        }
        userMap.put(id, user);
    }
}