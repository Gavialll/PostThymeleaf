package com.example.restapi.Controllers;

import com.example.restapi.Dao.Model.Advertisement;
import com.example.restapi.Dao.Model.Comment;
import com.example.restapi.Dao.Model.User;
import com.example.restapi.Dao.Repository.AdvertisementRepository;
import com.example.restapi.Dao.Repository.CommentRepository;
import com.example.restapi.Dao.Repository.UserRepository;
import com.example.restapi.Service.CommentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class PostController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdvertisementRepository advertisementRepository;
    @Autowired
    private CommentRepository commentRepository;

    ////////////////////////////////////////////////////////////////////////////
    //використовувати два контролера для get для відправки пустого обєкту на сторінку
    //і post для заповнення
    //
    //Добавлення нового користувача
    @PostMapping("/addPost")
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

        return "redirect:/allPost";
    }

    @GetMapping("/addPost")
    public String addPost(Model model){
        model.addAttribute("post", new Advertisement());
        return "addPost";
    }
    ////////////////////////////////////////////////////////////////////////////

    //Вивід всіх постів авторизованого користувача
    @GetMapping("/allPost")
    public String allPost(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());

        List<Advertisement> list = advertisementRepository.findAllByUser(user.getId());
        Collections.reverse(list);
        model.addAttribute("posts", list);
        return "allPost";
    }

    @GetMapping("/")
    public String indexAllPost(Model model){
        Iterable<Advertisement> list  = advertisementRepository.findAll();
        Collections.reverse((List<?>) list);
        model.addAttribute("posts", list);
        return "index";
    }

    @GetMapping("/post/{id}")
    public String getPost(@PathVariable int id, Model post, Model user, Model comment){
        Advertisement advertisement = advertisementRepository.findById(id).get();
        post.addAttribute("post", advertisement);
        user.addAttribute("user", userRepository.findById(advertisement.getUser()).get());

        List<CommentList> commentLists = new ArrayList<>();
        List<Comment> comments = commentRepository.findAllByAdvertisement(advertisement.getId());

        for(int i = 0; i < comments.size(); i++) {
            User user1 = userRepository.findById(comments.get(i).getUserId()).get();

            commentLists.add(new CommentList(comments.get(i).getMessage(), user1.getFirstName(), user1.getImg()));
        }
        Collections.reverse(commentLists);

        comment.addAttribute("comments", commentLists);
        return "postInfo";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam int id){
        advertisementRepository.deleteById(id);
        return "redirect:/allPost";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Advertisement post) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());

        post.setId(post.getId());
        post.setUser(user.getId());
        Date date = new Date();
        post.setData(date);
        if(post.getImg().isEmpty()){
            post.setImg("https://хлебов.рф/files/no_photo3.jpg");
        }
        advertisementRepository.save(post);

        return "redirect:/allPost";
    }

    @GetMapping("/update")
    public String update(@RequestParam int id, Model model){
        model.addAttribute("post", advertisementRepository.findById(id).get());

        return "updatePost";
    }

    @ModelAttribute("userAuth")
    public User user(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if("anonymousUser".equals(auth.getName())) {
            User user = new User();
            user.setId(0);
          return user;
        }
        else return userRepository.findByEmail(auth.getName());
    }

    @ModelAttribute("comment")
    public Comment comment(){
        return new Comment();
    }


}
