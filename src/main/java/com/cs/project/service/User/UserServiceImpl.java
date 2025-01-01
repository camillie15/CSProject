package com.cs.project.service.User;

import com.cs.project.service.User.UserService;
import com.cs.project.service.User.UserLogin;
import com.cs.project.model.User;
import com.cs.project.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Clase implementadora de todos los servicios del usuario
 *
 * @author nan2p
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService, UserLogin {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean userRegister(String name, String lastName, String email, String userName, String password) {
        User user = new User(name, lastName, email, userName, password);
        return userRepository.userRegister(user);
    }

    @Override
    public boolean UserAuthenticate(String email, String password, HttpSession session) {
        log.info("SERVICE UserAuthenticate");
        int value = userRepository.findUser(email, password);
        if (value >= 0) {
            session.setAttribute("userIdLogged", value);
            return true;
        } else {
            return false;
        }
    }
}
