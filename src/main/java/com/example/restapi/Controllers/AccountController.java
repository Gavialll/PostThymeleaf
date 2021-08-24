package com.example.restapi.Controllers;

import com.example.restapi.Dao.Model.User;
import com.example.restapi.Dao.Repository.AdvertisementRepository;
import com.example.restapi.Dao.Repository.UserRepository;
import com.example.restapi.Service.AuthId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AuthId authId;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdvertisementRepository advertisementRepository;

    @GetMapping("/{id}")
    public String account(@PathVariable int id, Model user, Model listPost) {
        user.addAttribute("user", userRepository.findById(id).get());
        listPost.addAttribute("posts", advertisementRepository.findAllByUser(id));
        return "account";
    }

    @GetMapping
    public String account(Model user, Model listPost) {
        user.addAttribute("user", userRepository.findById(authId.id()).get());
        listPost.addAttribute("posts", advertisementRepository.findAllByUser(authId.id()));
        return "account";
    }

    @GetMapping("/edit")
    public String edit (Model model){
        model.addAttribute("editUser", userRepository.findById(authId()).get());
        return "editAccount";
    }

    @PostMapping("/edit/{id}")
    public String edit(@ModelAttribute User user){
        user.setLogin("test");
        userRepository.save(user);
        return "redirect:/account/" + user.getId() ;
    }

//    @GetMapping("/account/delete")
//    public String delete(){
//        int id = userAuth().getId();
//        System.out.println("1");
//        commentRepository.deleteAllByUserId(id);
//        System.out.println("2");
//        advertisementRepository.deleteAllByUser(id);
//        System.out.println("3");
//        userRepository.deleteById(id);
//        System.out.println("4");
//        return "redirect:/login";
//    }

    @ModelAttribute
    public void session(HttpSession session){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if("anonymousUser".equals(auth.getName())) {
            session.setAttribute("authId", 0);
        }
        else session.setAttribute("authId", userRepository.findByEmail(auth.getName()).getId());
    }

    public int authId(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if("anonymousUser".equals(auth.getName())) {
            return 0;
        }
        else return userRepository.findByEmail(auth.getName()).getId();
    }
}
