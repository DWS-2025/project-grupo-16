package com.projectdws.alquilercoches.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import com.projectdws.alquilercoches.models.Car;
import com.projectdws.alquilercoches.models.User;
import com.projectdws.alquilercoches.models.Comment;
import com.projectdws.alquilercoches.services.CarService;
import com.projectdws.alquilercoches.services.CommentService;
import com.projectdws.alquilercoches.services.UserService;

@Controller
public class UsersController {

    private UserService userService;
    private CommentService commentService;
    private CarService carService;

    @Autowired
    public UsersController(UserService userService, CommentService commentService, CarService carService) {
        this.userService = userService;
        this.commentService = commentService;
        this.carService = carService;
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @GetMapping("/user/new-edit")
    public String showEditUserForm(Model model, User user) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("edit", false);
        return "new_user";
	}

    @PostMapping("/user/new-edit")
	public String createOrEditUser(Model model, User user) {
        if(user.getID() == 0) {
		    
            return "redirect:/user/" + user.getID();
        } else {
            userService.update(user);
            return "redirect:/user/" + user.getID();
        }
		
	}

    @GetMapping("/user/{id}")
    public String getUser(Model model, @PathVariable Long id) {
        Optional<User> opUser = userService.findById(id);
        if (opUser.isPresent()) {
            model.addAttribute("user", opUser.get());
            return "user";
        }
        return "user_not_found";
    }

    @GetMapping("/user/{id}/edit")
    public String editUser(Model model, @PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "edit_user";
        }
        return "user_not_found";
    }

}