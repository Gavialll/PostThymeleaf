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

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
public class AccountController {

    @Autowired
    private UserRepository userRepository;

    //return сторінки login
    @GetMapping("/login")
    public String login(){
        return "autorization";
    }

    @GetMapping("/account/{id}")
    public String account(@PathVariable int id, Model user, Model userAut, HttpSession session) {
        user.addAttribute("user", userRepository.findById(id).get());


//        Iterable<Advertisement> list = advertisementRepository.findAllByUser(id);
//        listPost.addAttribute("posts", list);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user1 = userRepository.findByEmail(auth.getName());
        userAut.addAttribute("user1", user1);
        return "account";
    }
}
