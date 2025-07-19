package com.cs.project.controller;

import com.cs.project.model.Comment;
import com.cs.project.model.Post;
import com.cs.project.service.Comment.CommentServiceImpl;
import com.cs.project.service.Post.PostServiceImpl;
import jakarta.servlet.http.HttpSession;
import java.util.List;
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
 * Clase que maneja las solicitudes HTTP de los comentarios
 *
 * @author Camillie Ayovi Villafuerte
 */
@Controller
@Slf4j
public class CommentController {

    private final PostServiceImpl postService;
    private final CommentServiceImpl commentService;

    @Autowired
    public CommentController(PostServiceImpl postService, CommentServiceImpl commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    /**
     * Método para la obtención de un post junto con sus comentarios y el formulario para la creación de un nuevo comentario
     *
     * @param postId identificador del post del que se van a mostrar los datos y comentarios
     * @param model pasa los datos del post y sus comentarios a la vista
     * @return retorna la plantilla de comentarios
     */
    @GetMapping("/comments/{id}")
    public String showComments(@PathVariable("id") int postId, Model model, HttpSession session) {
        Post post = postService.reviewExistentPost(postId);
        Comment commentAdd = new Comment();
        if (post != null) {
            List<Comment> comments = commentService.reviewExistedCommentsByPost(post.getPostId());
            model.addAttribute("post", post);
            model.addAttribute("comments", comments);
            model.addAttribute("commentAdd", commentAdd);
            model.addAttribute("userIdLogged", (int) session.getAttribute("userIdLogged"));
            model.addAttribute("rol", (int) session.getAttribute("userRolLogged"));
        } else {
            log.warn("CommentController / No se encontró el post con ese id: " + postId);
        }
        return "comments";
    }

    /**
     * Método que toma los datos de la solicitud POST para la creación de un comentario
     *
     * @param postId identificador del post al que se va a agregar el comentario
     * @param parameters parámetros de la solicitud
     * @param session sesión activada por el usuario
     * @return retorna la plantilla de comentarios
     */
    @PostMapping("/comments/{id}")
    public String createComment(@PathVariable("id") int postId, @RequestParam Map<String, String> parameters, HttpSession session) {
        String content = parameters.get("content");
        if (postService.reviewExistentPost(postId) != null) {
            commentService.reviewDataCommentForAdd(content, postId, session);
            log.info("CommentController / Comentario creado");
        } else {
            log.warn("CommentController / No se encontró el post con ese id: " + postId);
        }
        return "redirect:/comments/{id}";
    }

    /**
     * Método que toma los datos de la solicitud POST para actualizar el comentario
     *
     * @param postId identificador del post al que pertenece el comentario
     * @param commentId identificador del comentario a actualizar
     * @param parameters parámetros de la solicitud
     * @return retorna la plantilla de comentarios
     */
    @PostMapping("/comments/{postId}/update/{commentId}")
    public String updateComment(@PathVariable("postId") int postId, @PathVariable("commentId") int commentId, @RequestParam Map<String, String> parameters) {
        Comment comment = commentService.reviewExistedComment(commentId);
        String content = parameters.get("content");
        if (comment != null) {
            comment.setContent(content);
            commentService.reviewDataCommentForUpdate(comment);
            log.info("CommentController / Comentario con id: " + commentId + " actualizado");

        } else {
            log.warn("CommentController / Comentario no encontrado con id: " + commentId);
        }
        return "redirect:/comments/{postId}";
    }

    /**
     * Método que toma los datos de la solicitud POST para eliminar un comentario
     *
     * @param postId identificador del post al que pertenece el comentario
     * @param commentId identificador del comentario a eliminar
     * @return retorna la plantilla de comentarios
     */
    @PostMapping("/comments/{postId}/delete/{commentId}")
    public String deleteComment(@PathVariable("postId") int postId, @PathVariable("commentId") int commentId) {
        commentService.reviewCommentForDelete(commentId);
        log.info("CommentController / Comentario con id: " + commentId + " eliminado");
        return "redirect:/comments/{postId}";
    }
}
