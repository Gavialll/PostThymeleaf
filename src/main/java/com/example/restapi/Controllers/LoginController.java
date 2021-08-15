package com.example.restapi.Controllers;

import com.example.restapi.Dao.Model.Advertisement;
import com.example.restapi.Dao.Model.User;
import com.example.restapi.Dao.Repository.AdvertisementRepository;
import com.example.restapi.Dao.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdvertisementRepository advertisementRepository;

    //return сторінки login
    @GetMapping("/login")
    public String login(){
        return "autorization";
    }
    
    @GetMapping("/account")
    public String account(Model model){
        //получаєм user який авторизувався
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //передаєм в модель для html and thymeleaf
            model.addAttribute("user", userRepository.findByEmail(auth.getName()));


        return "account";
    }

    @GetMapping("/allAdvertisement")
    public String advertisement(Model model){

        Iterable<Advertisement> list = advertisementRepository.findAll();

        model.addAttribute("post", list);

        return "allPost";
    }

    @GetMapping("/allPost")
    public String getUserId(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());

        Iterable<Advertisement> list = advertisementRepository.findAllByUser(user.getId());

        model.addAttribute("posts", list);

        return "allPost";
    }

}
