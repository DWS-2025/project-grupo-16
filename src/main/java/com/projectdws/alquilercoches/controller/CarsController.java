package com.projectdws.alquilercoches.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.projectdws.alquilercoches.dto.SelectedDealership;
import com.projectdws.alquilercoches.models.Car;
import com.projectdws.alquilercoches.models.Comment;
import com.projectdws.alquilercoches.models.Dealership;
import com.projectdws.alquilercoches.models.User;
import com.projectdws.alquilercoches.services.CarService;
import com.projectdws.alquilercoches.services.CommentService;
import com.projectdws.alquilercoches.services.DealershipService;
import com.projectdws.alquilercoches.services.ImageService;

@Controller
public class CarsController {

    private final CarService carService;
    private final CommentService commentService;
    private final DealershipService dealershipService;
    private final ImageService imageService;

    @Autowired
    public CarsController(ImageService imageService, CarService carService, CommentService commentService, DealershipService dealershipService) {
        this.imageService = imageService;
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
        model.addAttribute("dealerships", dealershipService.findAll());
        model.addAttribute("carExists", true);
        model.addAttribute("editingCar", false);
        return "new_car";
    }

    @GetMapping("/car/{id}/new-edit")
    public String showEditCarForm(Model model, @PathVariable Long id, Car car) {
        List<Car> cars = new ArrayList<>();
        cars.add(carService.findById(id).get());
        model.addAttribute("priceError", true);
        //model.addAttribute("dealerships", dealershipService.findAll());
        model.addAttribute("car", carService.findById(id).get());
        model.addAttribute("carExists", false);
        model.addAttribute("editingCar", true);

        List<SelectedDealership> selectedDealerships = new ArrayList<>();
        for (Dealership dealership : dealershipService.findAll()) {
            if (dealership.getCars().contains(carService.findById(id).get())) {
                selectedDealerships.add(new SelectedDealership(dealership, true));
            } else {
                selectedDealerships.add(new SelectedDealership(dealership, false));
            }
        }
        model.addAttribute("dealerships", selectedDealerships);

        return "new_car";
    }

    /**
     * Create new car
     *
     * @throws IOException
     */
    @PostMapping(consumes = "multipart/form-data", value = "/car/new-edit")
    public String createOrEditCar(@RequestParam List<Long> dealershipIDs, @RequestParam MultipartFile imageFile, Model model, Car car) throws IOException {
        if(car.getID() != 0) {
            Optional <Car> opCar = carService.findById(car.getID());
            if(opCar.isPresent()) {
                car = opCar.get();
            } else {
                throw new RuntimeException();
            }
        }
        
        String imageName;
        if (!(imageFile.getOriginalFilename().equals(""))) {
            imageName = imageService.createImage(imageFile);
            String URL = imageService.getImage(imageName).getFile().getPath();
            System.out.println(URL);
            String[] split = URL.replace("\\", "/").split("/static");
            System.out.println(Arrays.toString(split));
            car.setImage(split[1]); 
            
        } else if(car.getID() == 0) {
            return "image_not_sent";
        }

        List<Dealership> selectedDealerships = new ArrayList<>();
        for (Long id : dealershipIDs) {
            Optional<Dealership> dealership = dealershipService.findById(id);
            dealership.ifPresent(selectedDealerships::add);
        }

        car.setDealerships(selectedDealerships);
        boolean noError = false;
        for (Dealership dealership : selectedDealerships) {
            if (car.getID() == 0 || !dealership.getCars().contains(carService.findById(car.getID()).get())) {
                noError = carService.save(car);
            } else {
                noError = carService.update(car);
            }
        }

        model.addAttribute("priceError", noError);

        if (noError) {
            return "redirect:/car/" + car.getID();
        }
        return "invalid_car_price";
    }

    /**
     * Get a car ID
     */
    @GetMapping("/car/{id}")
    public String getCar(@PathVariable Long id, Model model) throws InterruptedException {
        Thread.sleep(500);
        Optional<Car> opCar = carService.findById(id);
        if (opCar.isPresent()) {
            model.addAttribute("car", opCar.get());
            model.addAttribute("timestamp", System.currentTimeMillis());

            return "car";
        }
        return "car_not_found";
    }

    /**
     * Delete a car
     */
    @PostMapping("/car/{id}/delete")
    public String deleteCar(@PathVariable Long id) {
        Optional<Car> opCar = carService.findById(id);
        if (opCar.isPresent()) {
            for (Dealership dealership : opCar.get().getDealerships()) {
                dealership.getCars().remove(opCar.get());
            }
            if(opCar.isPresent()) {
                carService.delete(opCar.get());
            } 
            return "redirect:/cars";
        } else {
            return "car_not_found";
        }

    }

    // ----------- COMMENTS SECTION -----------
    /**
     * Post a comment
     */
    @PostMapping("/car/{id}/comment")
    public String newComment(@PathVariable Long id,
            @RequestParam String message,
            @RequestParam int numberStars,
            @RequestParam String authorName) {

        Optional<Car> opCar = carService.findById(id);

        if (opCar.isPresent()) {
            Car car = opCar.get();

            // Create comment with the received data
            Comment comment = new Comment();
            comment.setMessage(message);
            comment.setNumberStars(numberStars);

            // Create and assign author
            User author = new User();
            author.setName(authorName);
            comment.setAuthor(author);

            // Associate the comment and the car
            comment.setCarCommented(car);

            // Save the commend in the repository
            commentService.save(comment);

            // **Update the car with the new comments**
            car.getComments().add(comment); // Ensure the comment gets added to the list
            carService.update(car); // Ensure the car gets updated in the database

            return "redirect:/car/" + id; // Refresh the page with the updated data
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
            commentService.delete(commentId);
            return "redirect:/car/" + carId;
        }
        return "car_not_found";
    }
}
