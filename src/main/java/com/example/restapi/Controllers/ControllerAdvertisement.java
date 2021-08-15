package com.example.restapi.Controllers;

import com.example.restapi.Dao.Model.Advertisement;
import com.example.restapi.Dao.Model.User;
import com.example.restapi.Dao.Repository.AdvertisementRepository;
import com.example.restapi.Dao.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/advertisement")
public class ControllerAdvertisement {

    @Autowired
    AdvertisementRepository adsRepo;

    @Autowired
    UserRepository userRepo;

//    @PostMapping("/add")
//    public String add(@ModelAttribute Advertisement advertisement, Model model){
//        model.addAttribute("post", advertisement);
//        System.out.println("save" + advertisement.getName());
//        //adsRepo.save(advertisement);
//        return "save";
//    }

    @GetMapping("/getId")
    public Advertisement getId(@RequestParam Integer id){
        return adsRepo.findById(id).get();
    }

    @GetMapping("/getAll")
    public Iterable<Advertisement> getAll(){
        return adsRepo.findAll();
    }

    @GetMapping("/deleteId")
    public String getAll(@RequestParam Integer id){
        adsRepo.deleteById(id);
        return "delete";
    }

//    @GetMapping("/getAdvertisementUser")
//    public List<Advertisement> getUserId(){
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        User user = userRepo.findByEmail(auth.getName());
//
//        return adsRepo.findAllByUser(user.getId());
//    }
}
