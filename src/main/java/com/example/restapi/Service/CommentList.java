package com.example.restapi.Service;

import com.example.restapi.Dao.Model.Comment;
import com.example.restapi.Dao.Repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;


@Data
public class CommentList {
    @Autowired
    private UserRepository userRepository;

    private String message;
    private String firsName;
    private String img;

    public CommentList(String message, String firsName, String img){
        this.message = message;
        this.firsName = firsName;
        this.img = img;
    }
}
