package com.cs.project.controller;

import com.cs.project.model.Post;
import com.cs.project.service.Post.PostServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Clase que maneja las solicitudes HTTP de la página home
 * @author camil
 */
@Controller
public class HomeController {
    
    @Autowired
    private PostServiceImpl postService;

    /**
     * Método que obtiene la lista de posts y la agrega al modelo
     * @param model pasa la lista a la vista
     * @return retorna la plantilla home
     */
    @GetMapping("/home")
    public String home(Model model) {
        List<Post> posts = postService.reviewExistentPosts();
        model.addAttribute("posts", posts);
        return "home";
    }
}
