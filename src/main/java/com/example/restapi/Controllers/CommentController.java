package com.example.restapi.Controllers;

import com.example.restapi.Dao.Model.Comment;
import com.example.restapi.Dao.Model.User;
import com.example.restapi.Dao.Repository.CommentRepository;
import com.example.restapi.Dao.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentController {
    @Autowired
    private CommentRepository commentRepository;

    @PostMapping("/addComment")
    public String comment(@ModelAttribute Comment comment){
        commentRepository.save(comment);
        return "redirect:/post/" + comment.getAdvertisement();
    }
}
