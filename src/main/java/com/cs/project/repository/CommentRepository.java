
package com.cs.project.repository;

import com.cs.project.model.Comment;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Clase que se encarga de la interacción con la tabla Comments de la DB 
 * @author Camillie Ayovi Villafuerte
 */
@Slf4j
@Repository
public class CommentRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    /**
     * Método que ejecuta una sentencia SQL para obtener los registros de la tabla Comments según el id del post 
     * @param postId id del post del que se quiere obtener los comentarios
     * @return retorna la lista de comentarios
     */
    public List<Comment> getCommentsByPost(int postId){
        String query = "SELECT c.*, u.username AS userName FROM Comments c "
                + "JOIN Users u ON c.userId = u.userId "
                + "WHERE postId = ?";
        List<Comment> comments = jdbcTemplate.query(query, new CommentRowMapper(), postId);
        return comments != null ? comments : new ArrayList<>();
    }
    
    /**
     * Método que ejecuta una sentencia SQL para insertar un nuevo comentario a la tabla Comments
     * @param comment comentarios a insertar en la tabla
     */
    public void createComment(Comment comment){
        String query = "INSERT INTO Comments (content, userId, postId, createdDate) VALUES(?, ?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(query, comment.getContent(), comment.getUserId(), comment.getPostId(), comment.getCreatedDate());
        if(rowsAffected == 1){
            log.info("Comentario creado exitosamente");
        }else{
            log.info("Error en el registro del comentario");
        }
    }
}

