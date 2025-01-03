package com.cs.project.repository;

import com.cs.project.exception.DataBaseEjecuteException;
import com.cs.project.model.Post;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Clase que se encarga de la interacción con la tabla Posts de la DB 
 * @author Camillie Ayovi Villafuerte
 */
@Repository
@Slf4j
public class PostRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    /**
     * Método que ejecuta una consulta SQL para obtener todos los registros de la tabla Posts
     * @return retorna la lista de registros de la tabla Posts
     */
    public List<Post> getAllPosts(){
        try{
            String query = "SELECT p.*, u.username AS userName FROM Posts p " +
                   "JOIN Users u ON p.userId = u.userId";
            return jdbcTemplate.query(query, new PostRowMapper());
        }catch(DataBaseEjecuteException e){
            return new ArrayList<Post>();
        }
        
    }
    
    /**
     * Método que ejecuta una sentencia SQL para agregar el post registrado por el usuario a la tabla en la DB
     * @param post post a registrar
     */
    public void createPost(Post post){
        try{
            String query =  "INSERT INTO Posts (tittle, content, userId, createdDate) VALUES(?, ?, ?, ?)";
            jdbcTemplate.update(query, post.getTittle(), post.getContent(), post.getUserId(), post.getCreatedDate());
            log.info("Post creado exitosamente");
        }catch(Exception e){
            log.info("Error: " + e);
        }
    }
    
    /**
     * Método que ejecuta una sentencia SQL para obtener un post según el id
     * @param postId id del post a buscar
     * @return retorna el post encontrado o null
     */
    public Post getPostById(int postId) {
        String query = "SELECT p.*, u.username AS userName FROM Posts p " +
                   "JOIN Users u ON p.userId = u.userId "
                + "WHERE postId = ?";
        try {
            return jdbcTemplate.queryForObject(query, new PostRowMapper(), postId);
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Método que ejecuta una sentencia SQL para obtener la lista de post según el id del usuario
     * @param userId id del usuario al que pertenece el post
     * @return lista de post de un usuario
     */
    public List<Post> getPostsByUser(int userId) {
        String query = "SELECT p.*, u.username AS userName FROM Posts p " +
                   "JOIN Users u ON p.userId = u.userId"
                + "WHERE userId = ?";
        List<Post> posts = jdbcTemplate.query(query, new PostRowMapper(), userId);
        return posts != null ? posts : new ArrayList<>();
    }
}
