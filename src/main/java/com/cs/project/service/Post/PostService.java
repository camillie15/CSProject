package com.cs.project.service.Post;

import com.cs.project.model.Post;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 * Interfaz que define métodos para la interacción con los posts
 * @author Camillie Ayovi Villafuerte
 */
public interface PostService {
    List<Post> reviewExistentPosts();
    void reviewDataPostForCreate(String tittle, String content, HttpSession session);
    Post reviewExistentPost(int idPost);
}
