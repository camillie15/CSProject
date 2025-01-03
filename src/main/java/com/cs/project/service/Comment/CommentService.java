package com.cs.project.service.Comment;

import com.cs.project.model.Comment;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 * Interfaz que define métodos para la interacción con los comentarios
 * @author Camillie Ayovi Villafuerte
 */
public interface CommentService {
    void reviewDataCommentForAdd(String content, int postId, HttpSession session);
    public List<Comment> reviewExistedCommentsByPost(int postId);
}
