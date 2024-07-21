package ru.kata.spring.boot_security.demo.configs.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.enteties.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;


@Controller
@RequestMapping("/user")
public class UsersController {

    final UserService userService;
    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showUser(Principal principal,Model model) {
        User user =  userService.getUserByUserName(principal.getName());
        model.addAttribute("helloUser",principal.getName());
        model.addAttribute("userId",user.getId());
        model.addAttribute("username",user.getUsername());
        model.addAttribute("UserPassword",user.getPassword());
        model.addAttribute("userFirstName",user.getFirstName());
        model.addAttribute("userLastName",user.getLastName());
        model.addAttribute("userEmail",user.getEmail());
        model.addAttribute("userRole",user.getRoles());

        return "user/user";
    }


}
