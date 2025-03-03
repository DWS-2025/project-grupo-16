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

    /**
     * Get all cars
     */
    @GetMapping("/all")
    public String getCars(Model model) {
        model.addAttribute("cars", carService.findAll());
        return "cars";
    }

    /**
     * Create new car
     */
    @PostMapping("/new")
    public String newCar(@ModelAttribute Car car) {
        carService.save(car);
        return "redirect:/car/all";
    }

    /**
     * Get a car ID
     */
    @GetMapping("/{id}")
    public String getCarById(Model model, @PathVariable long id) {
        Optional<Car> car = carService.findById(id);
        if (car.isPresent()) {
            model.addAttribute("car", car.get());
            return "car";
        }
        return "car_not_found";
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
    @PostMapping("/{id}/edit")
    public String updateCar(@PathVariable long id, @ModelAttribute Car updatedCar) {
        Optional<Car> car = carService.findById(id);
        if (car.isPresent()) {
            carService.update(car.get(), updatedCar);
            return "redirect:/car/" + id;
        }
        return "car_not_found";
    }

    /**
     * Delete a car
     */
    @PostMapping("/{id}/delete")
    public String deleteCar(@PathVariable long id) {
        Optional<Car> car = carService.findById(id);
        if (car.isPresent()) {
            Long dealershipId = car.get().getDealership() != null ? car.get().getDealership().getID() : null;
            carService.delete(car.get());
            return dealershipId != null ? "redirect:/dealership/" + dealershipId : "redirect:/car/all";
        }
        return "car_not_found";
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

