
package com.cs.project.service.User;

/**
 * Implementacion con todos los metodos para el CRUD de un usuario
 * @author nan2p
 */
public interface UserService {
    
    boolean userRegister(String name, String lastName, String email, String userName, String password);
    
}
