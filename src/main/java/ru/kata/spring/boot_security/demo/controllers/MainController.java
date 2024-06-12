package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.spring.boot_security.demo.enteties.User;
import ru.kata.spring.boot_security.demo.security.UserDetailsImpl;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;


@Controller
public class MainController {

    //final UserRepository userRepository;
    final UserService userService;
@Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/index")
public String indexPage(){
    return "index";
}

@GetMapping("/user")
public String userPage(Principal principal  ){
  //  User user =

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       authentication.getPrincipal();
           UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
           System.out.println(userDetails.getUser());
    return "user";
}
    @GetMapping("/addNewUser")
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user );
        return "adduser";
    }
    @PostMapping("/saveUser")
    public String saveUser (@ModelAttribute("user") User user){
        userService.saveUser(user);
        return  "redirect:/user";
    }

}
