package com.cs.project.controller;

import com.cs.project.model.Post;
import com.cs.project.service.Post.PostServiceImpl;
import jakarta.servlet.http.HttpSession;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author camil
 */
@Controller
public class PostController {
        
    @Autowired
    private PostServiceImpl postService;

    @GetMapping("/createPost")
    public String createPostForm(Model model) {
        Post post = new Post();
        model.addAttribute("post", post);
        return "createPost";
    }
    
    @PostMapping("/createPost")
    public String createPost(@RequestParam Map<String, String> parameters, HttpSession session){
        String tittle = parameters.get("tittle");
        String content = parameters.get("content");
        postService.reviewDataPostForCreate(tittle, content, session);
        return "redirect:/home";
    }  
}