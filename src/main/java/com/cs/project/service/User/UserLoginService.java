package com.cs.project.service.User;

import jakarta.servlet.http.HttpSession;

/**
 * Interaz que permite autentificar al usuario dentro del sistema
 * @author nan2p
 */
public interface UserLoginService {
    
    boolean UserAuthenticate(String email, String password, HttpSession session);
    
}
