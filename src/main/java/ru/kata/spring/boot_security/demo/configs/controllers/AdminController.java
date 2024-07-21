package ru.kata.spring.boot_security.demo.configs.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.enteties.User;
import ru.kata.spring.boot_security.demo.services.UserService;


@Controller
@RequestMapping("/admin")
public class AdminController {
    final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping

    public String showAllUsers(Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        return "admin/adminPanel";
    }

    @GetMapping("/addNewUser")
    public String addNewUser(Model model) {
        model.addAttribute("user", new User());
        return "admin/adduser";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/deleteUser")
    public String deleteUser (@RequestParam("id") long id){

       userService.deleteUserById(id);
        return "redirect:/admin";
    }
    @GetMapping ("/updateUser")
    public String  update(@RequestParam ("id") long id,Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "admin/updateuser";
    }

        @PostMapping("/update")

        public String save (@ModelAttribute("user") User user){

            userService.updateUser(user);
            return "redirect:/admin";
        }
}


