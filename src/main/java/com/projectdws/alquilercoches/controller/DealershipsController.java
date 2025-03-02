package com.projectdws.alquilercoches.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.projectdws.alquilercoches.models.Dealership;
import com.projectdws.alquilercoches.services.DealershipService;

@Controller
public class DealershipsController {

	@Autowired
	private DealershipService dealershipService;

	@GetMapping("/")
	public String getDealershipsFromIndex(Model model){
		model.addAttribute("dealerships", dealershipService.findAll());
		System.out.println(dealershipService.findAll());
		return "index";
	}

	@GetMapping("/dealerships")
	public String getDealerships2(Model model){
		model.addAttribute("dealerships", dealershipService.findAll());
		return "index";
	}

	@GetMapping("/dealership/{id}")
	public String getDealershipById(Model model, @PathVariable long id) {
		Optional <Dealership> dealership = dealershipService.findById(id);
		if (dealership.isPresent()) {
			model.addAttribute("dealership", dealership.get());
			return "dealership";
		} else {
			return "dealership_not_found";
		}
	}

}
