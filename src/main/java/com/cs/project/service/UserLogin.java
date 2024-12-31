package com.cs.project.service;

import jakarta.servlet.http.HttpSession;

/**
 *
 * @author nan2p
 */
public interface UserLogin {
    
    boolean UserAuthenticate(String email, String password, HttpSession session);
    
}
