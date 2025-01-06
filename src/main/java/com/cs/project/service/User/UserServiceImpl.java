package com.cs.project.service.User;

import com.cs.project.model.Post;
import com.cs.project.service.User.UserService;
import com.cs.project.service.User.UserLogin;
import com.cs.project.model.User;
import com.cs.project.repository.PostRepository;
import com.cs.project.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import java.util.List;
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
public class UserServiceImpl implements UserService, UserLogin, UserPost {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public boolean userRegister(String name, String lastName, String email, String userName, String password) {
        if (!verifyUserRegister(email, userName)) {
            User user = new User(name, lastName, email, userName, password);
            return userRepository.userRegister(user);
        } else {
            return false;
        }
    }

    @Override
    public User findUser(HttpSession session) {
        int value = (int) session.getAttribute("userIdLogged");
        return userRepository.returnUser(value);
    }

    @Override
    public boolean userUpdate(String name, String lastName, String email, String userName, String password, HttpSession session) {
        if (!verifyUserRegister(email, userName)) {
            int userId = (Integer) session.getAttribute("userIdLogged");
            User user = new User(name, lastName, email, userName, password);
            return userRepository.updateUser(user, userId);
        } else {
            return false;
        }

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

    @Override
    public List<Post> getPostsByUser(HttpSession session) {
        int value = (Integer) session.getAttribute("userIdLogged");
        return postRepository.getPostsByUser(value);
    }

    private boolean verifyUserRegister(String email, String userName) {
        return userRepository.findEmail(email) || userRepository.findUserName(userName);
    }
}
