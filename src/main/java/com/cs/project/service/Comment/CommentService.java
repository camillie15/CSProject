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
    List<Comment> reviewExistedCommentsByPost(int postId);
    void reviewDataCommentForUpdate(Comment comment);
    Comment reviewExistedComment(int commentId);
    void reviewCommentForDelete(int commentId);
}
