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
     * @return retorna la plantilla del formulario crear post
     */
    @GetMapping("/createPost")
    public String viewPostForm(Model model, HttpSession session) {
        if ((int) session.getAttribute("userRolLogged") == 1) {
            Post post = new Post();
            model.addAttribute("post", post);
            model.addAttribute("rol", (int) session.getAttribute("userRolLogged"));
            return "createPost";
        } else {
            return "redirect:/home";
        }
    }

    /**
     * Método que toma los datos de la solicitud POST para la creación del post
     *
     * @param parameters parámetros de la solicitud
     * @param session sesión activada por el usuario
     * @return retorna la plantilla de home
     */
    @PostMapping("/createPost")
    public String createPost(@RequestParam Map<String, String> parameters, HttpSession session) {
        String tittle = parameters.get("tittle");
        String content = parameters.get("content");
        postService.reviewDataPostForCreate(tittle, content, session);
        log.info("PostController / Post creado");
        return "redirect:/home";
    }

    /**
     * Método que toma los datos de la solicitud POST para actualizar el post
     *
     * @param postId identificador del post a actualizar
     * @param parameters parámetros de la solicitud
     * @return retorna la plantilla de perfil del usuario
     */
    @PostMapping("/update/{postId}")
    public String updatePost(@PathVariable("postId") int postId, @RequestParam Map<String, String> parameters) {
        Post post = postService.reviewExistentPost(postId);
        String tittle = parameters.get("title");
        String content = parameters.get("content");
        if (post != null) {
            post.setTittle(tittle);
            post.setContent(content);
            postService.reviewDataPostForUpdate(post);
            log.info("PostController / Post con id: " + postId + " actualizado");
        } else {
            log.warn("PostController / Post no encontrado con id: " + postId);
        }
        return "redirect:/profile";
    }
    
    /**
     * Método para la visualización del formulario para la actualización del post
     * 
     * @param postId identificador del post a actualizar
     * @param model pasa el formulario a la vista
     * @return 
     */
    @GetMapping("/update/{postId}")
    public String viewFormUpdate(@PathVariable("postId") int postId, Model model, HttpSession session) {
        if ((int) session.getAttribute("userRolLogged") == 1) {
            Post post = postService.reviewExistentPost(postId);
            if (post != null) {
                model.addAttribute("post", post);
                model.addAttribute("rol", (int) session.getAttribute("userRolLogged"));
            } else {
                log.warn("PostController / Post no encontrado con id: " + postId);
            }
            return "updatePost";
        } else {
            return "redirect:/home";
        }
    }

    /**
     * Método que toma los datos de la solicitud POST para eliminar un post
     *
     * @param postId identificador del post a eliminar
     * @return retorna la plantilla de perfil del usuario
     */
    @PostMapping("/post/delete/{postId}")
    public String deletePost(@PathVariable("postId") int postId, HttpSession session) {
        postService.reviewPostForDelete(postId);
        log.info("PostController / Post con id: " + postId + " eliminado");
        if ((int) session.getAttribute("userRolLogged") == 1){
            return "redirect:/profile";
        } else {
            return "redirect:/home";
        }
    }
}
