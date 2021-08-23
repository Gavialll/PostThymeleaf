package com.example.restapi.Controllers;

import com.example.restapi.Dao.Model.Advertisement;
import com.example.restapi.Dao.Model.User;
import com.example.restapi.Dao.Repository.AdvertisementRepository;
import com.example.restapi.Dao.Repository.CommentRepository;
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
    @Autowired
    private AdvertisementRepository advertisementRepository;
    @Autowired
    private CommentRepository  commentRepository;

    //return сторінки login
    @GetMapping("/login")
    public String login(){
        return "autorization";
    }

    @GetMapping("/account/{id}")
    public String account(@PathVariable int id, Model user, Model listPost) {
        user.addAttribute("user", userRepository.findById(id).get());
        Iterable<Advertisement> list  = advertisementRepository.findAllByUser(id);
        listPost.addAttribute("posts", list);
        return "account";
    }

    @GetMapping("/account/edit")
    public String edit (Model model){
        model.addAttribute("editUser", userAuth());
        return "editAccount";
    }
    @PostMapping("/account/edit/{id}")
    public String edit(@ModelAttribute User user){
        user.setLogin("test");
        userRepository.save(user);
        return "redirect:/account/" + user.getId() ;
    }

    @GetMapping("/account/delete")
    public String delete(){
        int id = userAuth().getId();
        System.out.println("1");
        commentRepository.deleteAllByUserId(id);
        System.out.println("2");
        advertisementRepository.deleteAllByUser(id);
        System.out.println("3");
        userRepository.deleteById(id);
        System.out.println("4");
        return "redirect:/login";
    }

    @ModelAttribute("userAuth")
    public User userAuth(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if("anonymousUser".equals(auth.getName())) {
            User user = new User();
            user.setId(0);
            return user;
        }
        else return userRepository.findByEmail(auth.getName());
    }
}
