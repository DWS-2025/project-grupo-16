package com.projectdws.alquilercoches.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.projectdws.alquilercoches.models.Dealership;
import com.projectdws.alquilercoches.services.DealershipService;
import com.projectdws.alquilercoches.services.UserService;


@Controller
public class DealershipsController {

	@Autowired
	private DealershipService dealershipService;

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String getDealershipsFromIndex(Model model, Long id) {
		model.addAttribute("dealership", dealershipService.findAll());
		System.out.println(dealershipService.findAll());
		return "index";
	}


	@GetMapping("/{id}")
	public String getDealershipsAndUserFromIndex(Model model, @PathVariable Long id) {
		model.addAttribute("dealership", dealershipService.findAll());
		model.addAttribute("user", userService.findById(id));
		System.out.println(dealershipService.findAll());
		return "index";
	}


	@GetMapping("/dealership")
	public String getDealerships2(Model model) {
		model.addAttribute("dealership", dealershipService.findAll());
		return "index";
	}

	@GetMapping("/dealership/{id}")
	public String getDealershipById(Model model, @PathVariable Long id) {
		Optional<Dealership> dealership = dealershipService.findById(id);
		if (dealership.isPresent()) {
			model.addAttribute("dealership", dealership.get());
			return "dealership";
		} else {
			return "dealership_not_found";
		}
	}

	@GetMapping("/dealership/new-edit")
	public String showForm(
			Model model, @RequestParam(required = false) Long ID, String name, String location, String address,
			String tlf, String description) {/*
												 * not sure for now how i'll handle the ID thing, whether i'll use there
												 * being a coincidence of id with another dealership for an error of
												 * sorts or i'll use it to make it an "edit" form
												 */
		model.addAttribute("address", address);
		model.addAttribute("location", location);
		model.addAttribute("name", name);
		model.addAttribute("description", description);
		model.addAttribute("tlf", tlf);
		/*
		 * if (id > 0){
		 * 
		 * /*dealership exists and the form works for editing it
		 */
		/* } */
		return "new_dealership";
	} /*  */

	@PostMapping("/dealership/new-edit")
	public String createOrEditDealership(Model model, Dealership dealership) {
		if (dealership.getID() == 0) {
			/* dealership.setImage("dealership.image"); */
			dealershipService.save(dealership);
			return "redirect:/dealership/" + dealership.getID();
		} else {

			/* dealership.setImage("a"); */

			dealershipService.update(dealership);
			return "redirect:/dealership/" + dealership.getID();
		}

	}

}
