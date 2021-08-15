package com.example.restapi.Service;

import com.example.restapi.Dao.Model.User;
import com.example.restapi.Dao.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User get(int id){
        return userRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public void add(String name, String email){
        User user = new User();
        user.setFirstName(name);
        user.setLastName("lastname");
        user.setEmail(email);
        user.setPassword("password");
        user.setLogin("login");
        user.setImg("img");
        //Зберігаємо в БД
        userRepository.save(user);
    }



}
