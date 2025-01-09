package com.cs.project.repository;

import com.cs.project.model.Post;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Clase que se encarga de la interacción con la tabla Posts de la DB
 *
 * @author Camillie Ayovi Villafuerte
 */
@Repository
@Slf4j
public class PostRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Método que ejecuta una consulta SQL para obtener todos los registros de la tabla Posts
     * 
     * @return retorna la lista de registros de la tabla Posts
     */
    public List<Post> getAllPosts() {
        try {
            String query = "SELECT p.*, u.username AS userName FROM Posts p "
                    + "JOIN Users u ON p.userId = u.userId "
                    + "ORDER BY p.createdDate DESC";
            return jdbcTemplate.query(query, new PostRowMapper());
        } catch (DataAccessException e) {
            log.error("SQL query / Error: " + e);
            return new ArrayList<Post>();
        }
    }

    /**
     * Método que ejecuta una sentencia SQL para obtener un post según el id
     *
     * @param postId identificador del post a buscar
     * @return retorna el post encontrado o null
     */
    public Post getPostById(int postId) {
        try {
            String query = "SELECT p.*, u.username AS userName FROM Posts p "
                    + "JOIN Users u ON p.userId = u.userId "
                    + "WHERE postId = ?";
            return jdbcTemplate.queryForObject(query, new PostRowMapper(), postId);
        } catch (DataAccessException e) {
            log.error("SQL query / Error con ID de post: " + postId, e);
            return null;
        }
    }

    /**
     * Método que ejecuta una sentencia SQL para obtener la lista de post según el id del usuario
     *
     * @param userId identificador del usuario al que pertenece el post
     * @return lista de post de un usuario
     */
    public List<Post> getPostsByUser(int userId) {
        try {
            String query = "SELECT p.*, u.username AS userName FROM Posts p "
                    + "JOIN Users u ON p.userId = u.userId "
                    + "WHERE p.userId = ? "
                    + "ORDER BY p.createdDate DESC";
            List<Post> posts = jdbcTemplate.query(query, new PostRowMapper(), userId);
            return posts != null ? posts : new ArrayList<>();
        } catch (DataAccessException e) {
            log.error("SQL query / Error con ID de usuario: " + userId, e);
            return new ArrayList<>();
        }
    }

    /**
     * Método que ejecuta una sentencia SQL para insertar un post a la tabla Posts
     *
     * @param post post a insertar en la tabla
     */
    public void createPost(Post post) {
        try {
            String query = "INSERT INTO Posts (tittle, content, userId, createdDate) VALUES(?, ?, ?, ?)";
            jdbcTemplate.update(query, post.getTittle(), post.getContent(), post.getUserId(), post.getCreatedDate());
            log.info("SQL query / Post creado exitosamente");
        } catch (DataAccessException e) {
            log.error("SQL query / Error al insertar el post con ID: " + post.getPostId(), e);
        }
    }

    /**
     * Método que ejecuta una sentencia SQL para actualizar un post
     *
     * @param post post a actualizar
     */
    public void updatePost(Post post) {
        try {
            String query = "UPDATE Posts SET tittle = ? , content = ? WHERE postId = ?";
            jdbcTemplate.update(query, post.getTittle(), post.getContent(), post.getPostId());
            log.info("SQL query / Post actualizado exitosamente");
        } catch (DataAccessException e) {
            log.error("SQL query / Error al actualizar el post con ID: " + post.getPostId(), e);
        }
    }

    /**
     * Método que ejecuta una sentencia SQL para eliminar un post
     *
     * @param post post a eliminar
     */
    public void deletePost(Post post) {
        try {
            String query = "DELETE FROM Posts WHERE postId = ?";
            jdbcTemplate.update(query, post.getPostId());
            log.info("SQL query / Post eliminado exitosamente");
        } catch (DataAccessException e) {
            log.error("SQL query / Error al eliminar el post con ID: " + post.getPostId(), e);
        }
    }
}
