package com.projectdws.alquilercoches.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.projectdws.alquilercoches.models.Comment;
import com.projectdws.alquilercoches.models.Car;
import com.projectdws.alquilercoches.services.CarService;
import com.projectdws.alquilercoches.services.CommentService;
import com.projectdws.alquilercoches.services.DealershipService;
import com.projectdws.alquilercoches.services.UserService;

@Controller
@RequestMapping("/car")
public class CarsController {

    private final CarService carService;
    private final CommentService commentService;
    private final UserService userService;
    private final DealershipService dealershipService;

    @Autowired
    public CarsController(CarService carService, CommentService commentService,
                          UserService userService, DealershipService dealershipService) {
        this.carService = carService;
        this.commentService = commentService;
        this.userService = userService;
        this.dealershipService = dealershipService;
    }

	@GetMapping("/cars")
	public String getCars(Model model){
		model.addAttribute("cars", carService.findAll());
		return "cars";
	}

    /**
     * Get all cars
     */
    @GetMapping("/all")
    public String getAllCars(Model model) {
        model.addAttribute("cars", carService.findAll());
        return "cars";
    }

    /**
     * Create new car
     */
	@PostMapping("/car/new")
	public String newCar(Model model, Car car) {
		carService.save(car);
		return "new_car";
	}
    /**
     * Get a car ID
     */
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

    /**
     * Edit a car
     */
    @GetMapping("/{id}/edit")
    public String editCar(Model model, @PathVariable long id) {
        Optional<Car> car = carService.findById(id);
        if (car.isPresent()) {
            model.addAttribute("car", car.get());
            return "edit_car";
        }
        return "car_not_found";
    }

    /**
     * Save changes
     */
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

    /**
     * Delete a car
     */
	@PostMapping("/car/{id}/delete")
	public String deleteCar(@PathVariable long id) {
		Optional<Car> car = carService.findById(id);
		if (car.isPresent()) {
			carService.delete(car.get());
			return "redirect:/dealership/" + car.get().getDealership().getID();
		}else{
        return "car_not_found";
		}

    }

    // ----------- COMMENTS SECTION -----------

    /**
     * Post a comment
     */
    @PostMapping("/{id}/comment")
    public String newComment(@PathVariable long id, @ModelAttribute Comment comment) {
        Optional<Car> opCar = carService.findById(id);
        if (opCar.isPresent()) {
            commentService.save(opCar.get(), comment);
            return "redirect:/car/" + id;
        }
        return "car_not_found";
    }

    /**
     * Delete a comment
     */
    @PostMapping("/{carId}/comment/{commentId}/delete")
    public String deleteComment(@PathVariable Long carId, @PathVariable Long commentId) {
        Optional<Car> opCar = carService.findById(carId);
        if (opCar.isPresent()) {
            commentService.delete(commentId, opCar.get());
            return "redirect:/car/" + carId;
        }
        return "car_not_found";
    }
}

