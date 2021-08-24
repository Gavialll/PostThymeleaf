package com.example.restapi.Controllers;

import com.example.restapi.Dao.Model.Advertisement;
import com.example.restapi.Dao.Repository.AdvertisementRepository;
import com.example.restapi.Service.AuthId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private AdvertisementRepository advertisementRepository;
    @Autowired
    private AuthId authId;

    @GetMapping("/")
    public String indexAllPost(Model model){
        Iterable<Advertisement> list  = advertisementRepository.findAll();
        Collections.reverse((List<?>) list);
        model.addAttribute("posts", list);
        return "index";
    }

    @ModelAttribute
    public void session(HttpSession session){
        session.setAttribute("authId", authId.id());
    }
}
