package com.example.restapi.Controllers;

import com.example.restapi.Dao.Model.Advertisement;
import com.example.restapi.Dao.Model.Comment;
import com.example.restapi.Dao.Model.User;
import com.example.restapi.Dao.Repository.AdvertisementRepository;
import com.example.restapi.Dao.Repository.CommentRepository;
import com.example.restapi.Dao.Repository.UserRepository;
import com.example.restapi.Service.AuthId;
import com.example.restapi.Service.CommentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Controller
@RequestMapping("/post")
public class PostController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdvertisementRepository advertisementRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private AuthId authId;

    @PostMapping("/add")
    public String add(@ModelAttribute Advertisement post) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());
        post.setUser(user.getId());
        Date date = new Date();
        post.setData(date);
        if(post.getImg().isEmpty()){
            post.setImg("https://хлебов.рф/files/no_photo3.jpg");
        }
        advertisementRepository.save(post);
        return "redirect:/post/all";
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("post", new Advertisement());
        return "addPost";
    }
    ////////////////////////////////////////////////////////////////////////////

    //Вивід всіх постів авторизованого користувача
    @GetMapping("/all")
    public String allPost(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());

        List<Advertisement> list = advertisementRepository.findAllByUser(user.getId());
        Collections.reverse(list);
        model.addAttribute("posts", list);
        return "allPost";
    }

    @GetMapping("/{id}")
    public String getPost(@PathVariable int id, Model post, Model user, Model comment, Model authUser){
        Advertisement advertisement = advertisementRepository.findById(id).get();

        post.addAttribute("post", advertisement);
        user.addAttribute("user", userRepository.findById(advertisement.getUser()).get());
        if(authId.id() != 0) {
            authUser.addAttribute("authUser", userRepository.findById(authId.id()).get());
        }
        List<CommentList> commentLists = new ArrayList<>();
        List<Comment> comments = commentRepository.findAllByAdvertisement(advertisement.getId());

        for(Comment value : comments) {
            User user1 = userRepository.findById(value.getUserId()).get();
            commentLists.add(new CommentList(value.getMessage(), user1.getFirstName(), user1.getImg()));
        }

        Collections.reverse(commentLists);
        comment.addAttribute("comments", commentLists);
        return "postInfo";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam int id){
        advertisementRepository.deleteById(id);
        commentRepository.deleteAllByAdvertisement(id);
        return "redirect:/post/all";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Advertisement post) {
        post.setId(post.getId());
        post.setUser(authId.id());
        post.setData(new Date());
        if(post.getImg().isEmpty()){
            post.setImg("https://хлебов.рф/files/no_photo3.jpg");
        }
        advertisementRepository.save(post);

        return "redirect:/post/all";
    }

    @GetMapping("/update")
    public String update(@RequestParam int id, Model model){
        model.addAttribute("post", advertisementRepository.findById(id).get());
        return "updatePost";
    }

    @ModelAttribute("comment")
    public Comment comment(){
        return new Comment();
    }


}
