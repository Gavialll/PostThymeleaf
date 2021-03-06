package com.example.restapi.Controllers;

import com.example.restapi.Dao.Model.User;
import com.example.restapi.Dao.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/registration")
    public String addNewUser(@ModelAttribute User user) {
        user.setLogin("login");
        user.setImg("https://хлебов.рф/files/no_photo3.jpg");
        userRepository.save(user);
        return "redirect:/";
    }

    @GetMapping("/registration")
    public String addPost(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }
}
