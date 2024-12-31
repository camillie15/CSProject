package com.cs.project.repository;

import com.cs.project.exception.DataBaseEjecuteException;
import com.cs.project.model.User;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Clase repositorio con la conexión a la BD
 *
 * @author Erick Cordova
 */
@Repository
@Slf4j
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Registrar usuario en la Base de Datos
     *
     * @param user modelo de usuario a ser registrado
     * @return
     */
    public boolean userRegister(User user) {
        try {
            String script = "INSERT INTO Users (name, lastName, email, username, password) VALUES (?,?,?,?,?)";
            jdbcTemplate.update(script, user.getName(), user.getLastName(), user.getEmail(), user.getUserName(), user.getPassword());
            return true;
        } catch (DataBaseEjecuteException e) {
            log.info("Error al ejecutar comando" + e);
            return false;
        }
    }

    public int findUser(String email, String password) {
        String script = "SELECT * FROM Users WHERE email = ? AND password = ?";
        List<Integer> foundUser = jdbcTemplate.query(script, (rs, rowNum) -> rs.getInt("userId"), email, password);
        log.info(foundUser.get(0).toString());
        return foundUser.isEmpty() ? -1 : foundUser.get(0);
    }
}
