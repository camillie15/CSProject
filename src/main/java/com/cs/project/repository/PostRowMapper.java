package com.cs.project.repository;

import com.cs.project.model.Post;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 * Clase que implementa la interfaz RowMapper para mapear los resultados del query a un objeto Post
 * @author Camillie Ayovi Villafuerte
 */
public class PostRowMapper implements RowMapper<Post>{

    /**
     * Método propio de la interfaz que toma cada dato del ResultSet y lo asigna a un atributo del objeto Post
     * @param rs ResultSet con los datos de la consulta
     * @param rowNum numero de la fila
     * @return retorna el objeto Post
     * @throws SQLException maneja excepciones de la DB
     */
    @Override
    public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
        Post post = new Post();
        post.setPostId(rs.getInt("postId"));
        post.setTittle(rs.getString("tittle"));
        post.setContent(rs.getString("content"));
        post.setUserId(rs.getInt("userId"));
        post.setCreatedDate(rs.getDate("createdDate").toLocalDate());
        post.setUserName(rs.getString("userName"));
        return post;
    }
}
