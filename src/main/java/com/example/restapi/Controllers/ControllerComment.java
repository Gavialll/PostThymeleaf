package com.example.restapi.Controllers;

import com.example.restapi.Dao.Model.Advertisement;
import com.example.restapi.Dao.Model.Comment;
import com.example.restapi.Dao.Model.User;
import com.example.restapi.Dao.Repository.CommentRepository;
import com.example.restapi.Dao.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class ControllerComment {

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/getAllByAdvertisement")
    public List<Comment> getAll(@RequestParam int id){
        return commentRepository.findAllByAdvertisement(id);
    }

}
