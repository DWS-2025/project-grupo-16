package com.projectdws.alquilercoches.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.projectdws.alquilercoches.models.User;

@Component
public interface UserRepository extends JpaRepository<User, Long> {

}