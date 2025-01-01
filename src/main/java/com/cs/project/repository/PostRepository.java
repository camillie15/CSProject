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
 * Clase que se encarga de la interacción con la DB 
 * @author camil
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
            String query = "SELECT p.*, u.name AS userName FROM Posts p " +
                   "JOIN Users u ON p.userId = u.userId";
            return jdbcTemplate.query(query, new PostRowMapper());
        }catch(DataBaseEjecuteException e){
            return new ArrayList<Post>();
        }
        
    }
}
