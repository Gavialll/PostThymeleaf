package com.example.restapi.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//Роздача статичного контенту
@Controller
public class LoginController {

    //return сторінки login
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/account")
    public String page(){
        return "account";
    }

//    @GetMapping("/get")
//    public @ResponseBody String test(){
//
//        return "test";
//    }

}
