package com.cs.project.service.User;

import jakarta.servlet.http.HttpSession;

/**
 *
 * @author nan2p
 */
public interface UserLogin {
    
    boolean UserAuthenticate(String email, String password, HttpSession session);
    
}
