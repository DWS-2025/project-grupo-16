package com.projectdws.alquilercoches.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.projectdws.alquilercoches.models.Car;
import com.projectdws.alquilercoches.models.Comment;
import com.projectdws.alquilercoches.models.Dealership;
import com.projectdws.alquilercoches.models.User;
import com.projectdws.alquilercoches.services.CarService;
import com.projectdws.alquilercoches.services.CommentService;
import com.projectdws.alquilercoches.services.DealershipService;

@Controller
public class CarsController {

    private final CarService carService;
    private final CommentService commentService;
    private final DealershipService dealershipService;

    @Autowired
    public CarsController(CarService carService, CommentService commentService, DealershipService dealershipService) {
        this.carService = carService;
        this.commentService = commentService;
        this.dealershipService = dealershipService;
    }

    /**
     * Get all cars
     */
    @GetMapping("/cars")
    public String getAllCars(Model model) {
        model.addAttribute("cars", carService.findAll());
        return "cars";
    }

    @GetMapping("/car/new-edit")
    public String showEditCarForm(Model model, Car car) {
        model.addAttribute("priceError", true);
        model.addAttribute("cars", carService.findAll());
        model.addAttribute("edit", false);
        model.addAttribute("dealerships", dealershipService.findAll());
        return "new_car";
	}


    @GetMapping("/car/{id}/new-edit")
    public String showEditCarForm(Model model, @PathVariable long id, Car car) {
        List <Car> cars = new ArrayList<>();
        cars.add(carService.findById(id).get());
        model.addAttribute("priceError", true);
        model.addAttribute("cars", cars);
        model.addAttribute("edit", true);
        model.addAttribute("dealerships", dealershipService.findAll());
        return "new_car";
	}

    /**
     * Create new car
     */
	@PostMapping("/car/new-edit")
	public String createOrEditCar(Model model, Car car) {
        if(car.getID() == 0) {
            car.setImage("car.image");
            car.setDealership(dealershipService.findById(car.getDealership().getID()).get());
            boolean error = carService.save(car);
            model.addAttribute("priceError", !error);
            if(!error) return "redirect:/car/new-edit";
		    return "redirect:/car/" + car.getID();
        } else {
            boolean error = carService.save(car);
            model.addAttribute("priceError", !error);
            if(!error) return "redirect:/car/" + car.getID() + "/new-edit";
            car.setImage("a");
            Dealership dealership = dealershipService.findById(car.getDealership().getID()).get();
            car.setDealership(dealership);
            carService.update(car);
            return "redirect:/car/" + car.getID();
        }
		
	}
    /**
     * Get a car ID
     */
	@GetMapping("/car/{id}")
public String getCar(@PathVariable long id, Model model) {
    Optional<Car> opCar = carService.findById(id);
    if (opCar.isPresent()) {
        model.addAttribute("car", opCar.get()); // <- Aquí se añade el coche con su ID
        return "car";
    }
    return "car_not_found";
}

    /**
     * Edit a car
     */
    @GetMapping("/car/{id}/edit")
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
			carService.update(updatedCar);
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
			carService.delete(id);
            car.get().getDealership().getCars().remove(car.get());
			return "redirect:/dealership/" + car.get().getDealership().getID();
		}else{
        return "car_not_found";
		}

    }

    // ----------- COMMENTS SECTION -----------

    /**
     * Post a comment
     */
    @PostMapping("/car/{id}/comment")
    public String newComment(@PathVariable long id, 
                             @RequestParam String message, 
                             @RequestParam int numberStars, 
                             @RequestParam String authorName) {  
    
        Optional<Car> opCar = carService.findById(id);
    
        if (opCar.isPresent()) {
            Car car = opCar.get();
            
            // Crear comentario con los datos recibidos
            Comment comment = new Comment();
            comment.setMessage(message);
            comment.setNumberStars(numberStars);
    
            // Crear y asignar autor
            User author = new User();
            author.setName(authorName);
            comment.setAuthor(author);
    
            // Asociar comentario con el coche
            comment.setCarCommented(car);
    
            // Guardar el comentario en el repositorio
            commentService.save(car, comment);
    
            // **Actualizar el coche con los nuevos comentarios**
            car.getComments().add(comment);  // Asegura que el comentario se añade a la lista
            carService.update(car);  // Asegurar que el coche se actualiza en la BD
    
            return "redirect:/car/" + id;  // Recargar la página con los datos actualizados
        }
    
        return "car_not_found";
    }
    


    /**
     * Delete a comment
     */
    @PostMapping("/car/{carId}/comment/{commentId}/delete")
    public String deleteComment(@PathVariable Long carId, @PathVariable Long commentId) {
        Optional<Car> opCar = carService.findById(carId);
        if (opCar.isPresent()) {
            commentService.delete(commentId, opCar.get());
            return "redirect:/car/" + carId;
        }
        return "car_not_found";
    }
}

