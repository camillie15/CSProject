package com.cs.project.service.Comment;

import com.cs.project.model.Comment;
import com.cs.project.repository.CommentRepository;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Clase que implementa la interfaz CommentService
 * @author Camillie Ayovi Villafuerte
 */
@Service
@Slf4j
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    
    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    
    /**
     * Método que asigna los valores a los atributos del objeto Comment
     * @param content contenido del comentario
     * @param postId id del post al que pertenece el comentario
     * @param session sesión activada por el usuario
     */
    @Override
    public void reviewDataCommentForAdd(String content, int postId, HttpSession session) {
        int userId = (int) session.getAttribute("userIdLogged");
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setPostId(postId);
        comment.setCreatedDate( LocalDate.now());
        comment.setUserId(userId);
        commentRepository.createComment(comment);
    }

    /**
     * Método que obtiene la lista de los comentarios por post
     * @param postId id del post al que pertenecen los comentarios
     * @return 
     */
    @Override
    public List<Comment> reviewExistedCommentsByPost(int postId) {
        return commentRepository.getCommentsByPost(postId);
    }
    
}
