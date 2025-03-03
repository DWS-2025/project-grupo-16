package com.projectdws.alquilercoches.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.projectdws.alquilercoches.models.Comment;
import com.projectdws.alquilercoches.models.Car;
import com.projectdws.alquilercoches.models.Dealership;
import com.projectdws.alquilercoches.services.CarService;
import com.projectdws.alquilercoches.services.CommentService;
import com.projectdws.alquilercoches.services.DealershipService;
import com.projectdws.alquilercoches.services.UserService;

@Controller
public class CarsController {

    @Autowired
	private CarService carService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private UserService userService;

    @Autowired
	private DealershipService dealershipService;

	@GetMapping("/cars")
	public String getCars(Model model){
		model.addAttribute("cars", carService.findAll());
		return "cars";
	}

	@PostMapping("/car/new")
	public String newCar(Model model, Car car) {
		carService.save(car);
		return "new_car";
	}

	@GetMapping("/car/{id}")
	public String getCarById(Model model, @PathVariable long id) {
		Optional<Car> car = carService.findById(id);
		if (car.isPresent()) {
			model.addAttribute("car", car.get());
			return "car";
		} else {
			return "car_not_found";
		}
	}

	@GetMapping("/car/{id}/edit")
	public String editCar(Model model, @PathVariable long id) {
		Optional<Car> car = carService.findById(id);
		if (car.isPresent()) {
			model.addAttribute("car", car.get());
			return "new_car";
		} else {
			return "car_not_found";
		}
	}

	@PostMapping("/car/{id}/edit")
	public String updateCar(Model model, @PathVariable long id, Car updatedCar) {
		Optional<Car> car = carService.findById(id);
		if (car.isPresent()) {
			Car oldCar = car.get();
			carService.update(oldCar, updatedCar);
			return "redirect:/car/" + id;
		} else {
			return "car_not_found";
		}
	}

	@PostMapping("/car/{id}/delete")
	public String deleteCar(@PathVariable long id) {
		Optional<Car> car = carService.findById(id);
		if (car.isPresent()) {
			carService.delete(car.get());
			return "redirect:/dealership/" + car.get().getDealership().getID();
		} else {
			return "car_not_found";
		}
	}

	@PostMapping("/car/{Id}")
	public String newComment(@PathVariable long Id, Comment comment) {
		Optional<Car> opCar = carService.findById(Id);
		if (opCar.isPresent()) {
			Car car = opCar.get();
			commentService.save(car, comment);
			return "redirect:/car/" + Id;
		} else {
			return "car_not_found";
		}
	}

	@PostMapping("/car/{Id}")
	public String deleteComment(@PathVariable Long carId, Long commentId) {
        Optional<Car> opCar = carService.findById(carId);
		if (opCar.isPresent()) {
			Car car = opCar.get();
			commentService.delete(commentId, car);
			return "redirect:/car/" + carId;
		} else {
			return "car_not_found";
		}
		
	}

}
