package com.cs.project.service.Comment;

import com.cs.project.model.Comment;
import com.cs.project.repository.CommentRepository;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
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
     * Método que asigna los valores a los atributos del objeto Comment para su registro en la capa de datos
     * 
     * @param content contenido del comentario
     * @param postId identificador del post al que pertenece el comentario
     * @param session sesión activada por el usuario
     */
    @Override
    public void reviewDataCommentForAdd(String content, int postId, HttpSession session) {
        int userId = (int) session.getAttribute("userIdLogged");
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setPostId(postId);
        comment.setCreatedDate( LocalDateTime.now());
        comment.setUserId(userId);
        commentRepository.createComment(comment);
    }

    /**
     * Método que obtiene la lista de los comentarios por post de la capa de datos
     * 
     * @param postId identificador del post al que pertenecen los comentarios
     * @return 
     */
    @Override
    public List<Comment> reviewExistedCommentsByPost(int postId) {
        return commentRepository.getCommentsByPost(postId);
    }

    /**
     * Método que recibe el objeto comment para su actualización en la capa de datos
     * 
     * @param comment comentario a actualizar
     */
    @Override
    public void reviewDataCommentForUpdate(Comment comment) {
        commentRepository.updateComment(comment);
    }

    /**
     * Método que valida la existencia de un comentario según su identificador
     * 
     * @param commentId identificador del comentario a buscar
     * @return retorna el comentario con el identificador enviado
     */
    @Override
    public Comment reviewExistedComment(int commentId) {
        return commentRepository.getCommentById(commentId);
    }

    /**
     * Método que recibe el identificador del objeto comment para su eliminación en la capa de datos
     * 
     * @param commentId identificador del comentario a eliminar
     */
    @Override
    public void reviewCommentForDelete(int commentId) {
        Comment comment = reviewExistedComment(commentId);
        commentRepository.deleteComment(comment);
    }
    
}
