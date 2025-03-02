package com.projectdws.alquilercoches.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.projectdws.alquilercoches.services.UserService;


@Controller
public class UsersController {
    
    @Autowired
    private UserService userService;

    /*
    @GetMapping("/users")
    public String getUsers(Model model){
        model.addAttribute("users", userService.findAll());
        return "users";
    }
    */
}