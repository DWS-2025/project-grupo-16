package com.projectdws.alquilercoches.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectdws.alquilercoches.models.Dealership;
import com.projectdws.alquilercoches.repository.DealershipRepository;

@Service
public class DealershipService {
	
	@Autowired
	private DealershipRepository dealershipRepository;

	public List<Dealership> findAll() {
		return dealershipRepository.findAll();
	}

	public Optional<Dealership> findById(long id) {
		return dealershipRepository.findById(id);
	}

	public void save(Dealership dealership) {
		dealershipRepository.save(dealership);		
	}

	public void update(Dealership dealership) {
		dealershipRepository.save(dealership);  
	}

}