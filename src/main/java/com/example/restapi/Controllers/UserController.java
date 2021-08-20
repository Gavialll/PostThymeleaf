package com.example.restapi.Controllers;

import com.example.restapi.Dao.Model.User;
import com.example.restapi.Dao.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    //Ініціалізаця репозиторія БД
    @Autowired
    private UserRepository usersRepository;

    @GetMapping("/add") // Map ONLY POST Requests
    public  @ResponseBody String addNewUser (
            @RequestParam String name,
            @RequestParam String email) {

        User user = new User();
        user.setFirstName(name);
        user.setLastName("lastname");
        user.setEmail(email);
        user.setPassword("password");
        user.setLogin("login");
        user.setImg("img");
        //Зберігаємо в БД
        usersRepository.save(user);
        return "Saved";
    }

    @GetMapping("/getId")
    public @ResponseBody User getUserById(@RequestParam Integer id) {
        return usersRepository.findById(id).get();
    }

    @GetMapping("/getAll")
    public @ResponseBody Iterable<User> getAllUsers(){
        return usersRepository.findAll();
    }
}