package com.example.restapi.Service;

import com.example.restapi.Dao.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthId {
    @Autowired
    private UserRepository userRepository;

    public int id(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if("anonymousUser".equals(auth.getName())) {
            return 0;
        }
        else return userRepository.findByEmail(auth.getName()).getId();
    }
}
