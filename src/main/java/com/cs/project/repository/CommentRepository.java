package com.cs.project.repository;

import com.cs.project.model.Comment;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Clase que se encarga de la interacción con la tabla Comments de la DB
 *
 * @author Camillie Ayovi Villafuerte
 */
@Slf4j
@Repository
public class CommentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Método que ejecuta una sentencia SQL para obtener los registros de la tabla Comments según el id del post
     *
     * @param postId id del post del que se quiere obtener los comentarios
     * @return retorna la lista de comentarios
     */
    public List<Comment> getCommentsByPost(int postId) {
        try {
            String query = "SELECT c.*, u.username AS userName FROM Comments c "
                    + "JOIN Users u ON c.userId = u.userId "
                    + "WHERE postId = ? "
                    + "ORDER BY c.createdDate ASC";
            return jdbcTemplate.query(query, new CommentRowMapper(), postId);
        } catch (DataAccessException e) {
            log.error("SQL query / Error: " + e);
            return new ArrayList<Comment>();
        }
    }

    /**
     * Método que ejecuta una sentencia SQL para obtener un comentario según el id
     *
     * @param commentId identificador del comentario a buscar
     * @return retorna el comentario encontrado o null
     */
    public Comment getCommentById(int commentId) {
        try {
            String query = "SELECT c.*, u.username AS userName FROM Comments c "
                    + "JOIN Users u ON c.userId = u.userId "
                    + "WHERE commentId = ?";
            return jdbcTemplate.queryForObject(query, new CommentRowMapper(), commentId);
        } catch (DataAccessException e) {
            log.error("SQL query / Error con ID de comentario: " + commentId, e);
            return null;
        }
    }

    /**
     * Método que ejecuta una sentencia SQL para insertar un comentario a la tabla Comments
     *
     * @param comment comentario a insertar en la tabla
     */
    public void createComment(Comment comment) {
        try {
            String query = "INSERT INTO Comments (content, userId, postId, createdDate) VALUES(?, ?, ?, ?)";
            jdbcTemplate.update(query, comment.getContent(), comment.getUserId(), comment.getPostId(), comment.getCreatedDate());
            log.info("SQL query / Comentario creado exitosamente");
        } catch (DataAccessException e) {
            log.error("SQL query / Error al insertar el comentario con ID : " + comment.getCommentId(), e);

        }
    }

    /**
     * Método que ejecuta una sentencia SQL para actualizar un comentario
     *
     * @param comment comentario a actualizar
     */
    public void updateComment(Comment comment) {
        try {
            String query = "UPDATE Comments SET content = ? WHERE commentId = ?";
            jdbcTemplate.update(query, comment.getContent(), comment.getCommentId());
            log.info("SQL query / Comentario actualizado exitosamente");
        } catch (DataAccessException e) {
            log.error("SQL query / Error al actualizar el commentario con ID " + comment.getCommentId(), e);

        }
    }

    /**
     * Método que ejecuta una sentencia SQL para eliminar un comentario
     *
     * @param comment comentario a eliminar
     */
    public void deleteComment(Comment comment) {
        try {
            String query = "DELETE FROM Comments WHERE commentId = ?";
            jdbcTemplate.update(query, comment.getCommentId());
            log.info("SQL query / Comentario eliminado exitosamente");
        } catch (DataAccessException e) {
            log.error("SQL query / Error al eliminar el commentario con ID " + comment.getCommentId(), e);

        }
    }
}
