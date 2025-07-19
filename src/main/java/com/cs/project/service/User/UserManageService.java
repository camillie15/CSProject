
package com.cs.project.service.User;

import com.cs.project.model.User;
import jakarta.servlet.http.HttpSession;

/**
 * Implementacion con todos los metodos para el CRUD de un usuario
 * @author nan2p
 */
public interface UserManageService {
    
    boolean userRegister(String name, String lastName, String email, String userName, String password, int rol);
    
    User findUser(HttpSession session);
    
    boolean userUpdate(String name, String lastName, String email, String userName, String password, HttpSession session);
    
}
