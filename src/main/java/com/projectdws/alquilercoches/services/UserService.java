package com.projectdws.alquilercoches.services;

import com.projectdws.alquilercoches.models.Car;
import com.projectdws.alquilercoches.models.User;
import com.projectdws.alquilercoches.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getLoggedUser() {
        List<User> users = userRepository.findAll();
        if (!users.isEmpty()) {
            return Optional.of(users.get(0));
        }
        return Optional.empty(); 
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

	public Optional<User> findById(long id) {
		return userRepository.findById(id);
	}

	public void save(User user) {
		userRepository.save(user);
	}

    public void update(User user) {
		userRepository.save(user);
	}
	

    public void delete(long id) {
        userRepository.delete(id);
    }
}
