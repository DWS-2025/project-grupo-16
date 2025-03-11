package com.projectdws.alquilercoches.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.projectdws.alquilercoches.models.Dealership;
import com.projectdws.alquilercoches.services.DealershipService;

@Controller
public class DealershipsController {

	@Autowired
	private DealershipService dealershipService;

	@GetMapping("/")
	public String getDealershipsFromIndex(Model model){
		model.addAttribute("dealership", dealershipService.findAll());
		System.out.println(dealershipService.findAll());
		return "index";
	}

	@GetMapping("/dealership")
	public String getDealerships2(Model model){
		model.addAttribute("dealership", dealershipService.findAll());
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

	@GetMapping("/dealership/new-edit")
	public String showForm(@RequestParam(required = false) Long ID, Model model){/*not sure for now how i'll handle the ID thing, whether i'll use there being a coincidence of id with another dealership for an error of sorts or i'll use it to make it an "edit" form */
	
		return "new_dealership";
	} /*  */


}
