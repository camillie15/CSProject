package com.cs.project.controller;

import com.cs.project.model.Post;
import com.cs.project.service.Post.PostServiceImpl;
import jakarta.servlet.http.HttpSession;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Clase que maneja las solicitudes HTTP de los post
 *
 * @author Camillie Ayovi Villafuerte
 */
@Controller
@Slf4j
public class PostController {

    private final PostServiceImpl postService;

    @Autowired
    public PostController(PostServiceImpl postService) {
        this.postService = postService;
    }

    /**
     * Método para la visualización del formulario para la creación del post
     *
     * @param model pasa el formulario a la vista
     * @return retorna la plantilla de crear post
     */
    @GetMapping("/createPost")
    public String createPostForm(Model model) {
        Post post = new Post();
        model.addAttribute("post", post);
        return "createPost";
    }

    /**
     * Método que toma los datos de la solicitud POST para la creación del post
     *
     * @param parameters parámetros de la solicitud
     * @param session sesión activada por el usuario
     * @return retorna la plantilla de crear post
     */
    @PostMapping("/createPost")
    public String createPost(@RequestParam Map<String, String> parameters, HttpSession session) {
        String tittle = parameters.get("tittle");
        String content = parameters.get("content");
        postService.reviewDataPostForCreate(tittle, content, session);
        return "redirect:/home";
    }

    @PostMapping("/post/update/{postId}")
    public String updatePost(@PathVariable("postId") int postId, @RequestParam Map<String, String> parameters) {
        try {
            Post post = postService.reviewExistentPost(postId);
            String tittle = parameters.get("title");
            String content = parameters.get("content");
            if (post != null) {
                post.setTittle(tittle);
                post.setContent(content);
                postService.reviewDataPostForUpdate(post);
            } else {
                log.info("Posr no encontrado con id: " + postId);
            }
        } catch (Exception e) {
            log.error("Error: " + e);
        }
        return "redirect:/profile";
    }
    
    @PostMapping("/post/delete/{postId}")
    public String deletePost(@PathVariable("postId") int postId){
        try {
            postService.reviewPostForDelete(postId);
        } catch (Exception e) {
            log.error("Error: " + e);
        }
        return "redirect:/profile";
    }
}
