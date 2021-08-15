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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Controller
public class AddPost {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdvertisementRepository advertisementRepository;

    ////////////////////////////////////////////////////////////////////////////
    //використовувати два контролера для get для відправки пустого обєкту на сторінку
    //і post для заповнення
    @PostMapping("/addPost")
    public String add(@ModelAttribute Advertisement post) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());

        post.setUser(user.getId());

        Date date = new Date();
        post.setData(date);

        advertisementRepository.save(post);

        return "redirect:/allPost";
    }

    @GetMapping("/addPost")
    public String addPost(Model model){
        model.addAttribute("post", new Advertisement());
        return "addPost";
    }
    ////////////////////////////////////////////////////////////////////////////

}
