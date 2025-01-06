package com.cs.project.repository;

import com.cs.project.model.Comment;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 * Clase que implementa la interfaz RowMapper para mapear los resultados del query a un objeto Comment
 * @author Camillie Ayovi Villafuerte
 */
public class CommentRowMapper implements RowMapper<Comment>{
    
    /**
     * Método propio de la interfaz que toma cada dato del ResultSet y lo asigna a un atributo del objeto Comment
     * @param rs ResultSet con los datos de la consulta
     * @param rowNum numero de la fila
     * @return retorna el objeto Comment
     * @throws SQLException maneja excepciones de la DB
     */
    @Override
    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Comment comment = new Comment();
        comment.setCommentId(rs.getInt("commentId"));
        comment.setContent(rs.getString("content"));
        comment.setPostId(rs.getInt("postId"));
        comment.setUserId(rs.getInt("userId"));
        comment.setCreatedDate(rs.getTimestamp("createdDate").toLocalDateTime());
        comment.setUserName(rs.getString("userName"));
        return comment;
    }
}
