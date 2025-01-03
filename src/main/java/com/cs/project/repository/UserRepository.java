package com.cs.project.repository;

import com.cs.project.exception.DataBaseEjecuteException;
import com.cs.project.model.User;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
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
            log.info("usuario registrado");
            return true;
        } catch (DataAccessException e) {
            log.info("Error al ejecutar comando" + e);
            return false;
        }
    }

    /**
     * Busca el usuario registrado en el sistema
     *
     * @param email email del usuario
     * @param password contraseña del usuario
     * @return EL id del usuario encontrado o -1 si no encuentra nada
     */
    public int findUser(String email, String password) {
        String script = "SELECT * FROM Users WHERE email = ? AND password = ?";
        try {
            List<Integer> foundUser = jdbcTemplate.query(script, (rs, rowNum) -> rs.getInt("userId"), email, password);
            log.info(foundUser.get(0).toString());
            return foundUser.isEmpty() ? -1 : foundUser.get(0);
        } catch (Exception e) {
            log.info("DONT USER FOUND " + e);
            return -1;
        }
    }
    
    /**
     * Buscar si existe en usuario con ese email registrado
     * @param email email a buscar en el sistema 
     * @return true si existe, false si no existe
     */
    public boolean findEmail(String email) {
        String script = "SELECT Count(*) FROM Users WHERE email = ?";
        try {
            int value = jdbcTemplate.queryForObject(script, Integer.class, email);
            log.info("Email user found" + value);
            return value > 0;
        } catch (EmptyResultDataAccessException e) {
            return false;
        } catch (DataAccessException e) {
            return false;

        }
    }
    
        /**
     * Buscar si existe en usuario con ese email registrado
     * @param userName username a buscar en el sistema
     * @return true si existe, false si no existe
     */
    public boolean findUserName(String userName) {
        String script = "SELECT Count(*) FROM Users WHERE username = ?";
        try {
            int value = jdbcTemplate.queryForObject(script, Integer.class, userName);
            log.info("UserName user found" + value);
            return value > 0;
        } catch (EmptyResultDataAccessException e) {
            return false;
        } catch (DataAccessException e) {
            return false;
        }
    }
    
}
