package com.example.restapi.Dao.Controllers;

import com.example.restapi.Dao.Model.Advertisement;
import com.example.restapi.Dao.Repository.AdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/advertisement")
public class ControllerAdvertisement {

    @Autowired
    AdvertisementRepository adsRepo;

    @PostMapping("/add")
    public String add(@RequestBody Advertisement advertisement){
        adsRepo.save(advertisement);
        return "save";
    }

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

    @GetMapping("/name")
    public String get(){
        return adsRepo.findById(1).get().getName();
    }
}
