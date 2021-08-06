package com.example.restapi.Controllers;

import com.example.restapi.Dao.Model.User;
import com.example.restapi.Dao.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


//Роздача статичного контенту
@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    //return сторінки login
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    
    @GetMapping("/account")
    public String account(Model model){
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            model.addAttribute("user", userRepository.findByEmail(auth.getName()));
        return "account";
    }

}
