package com.projectdws.alquilercoches.services;

import com.projectdws.alquilercoches.models.Car;
import com.projectdws.alquilercoches.models.User;
import com.projectdws.alquilercoches.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @ModelAttribute("user")
	public User getLoggedUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    
    if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof User) {
            Long id = ((User) principal).getID();
            Optional<User> userOptional = this.findById(id);
            return userOptional.orElse(null);
        }
    }
    return null;
	}

    public List<User> findAll() {
        return userRepository.findAll();
    }

	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	public void save(User user) {
		userRepository.save(user);
	}

    public void update(User user) {
		userRepository.save(user);
	}
	

    public void delete(User user) {
        userRepository.delete(user);
    }
}
