package com.cs.project.service.User;

import com.cs.project.model.Post;
import com.cs.project.model.User;
import com.cs.project.repository.PostRepository;
import com.cs.project.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cs.project.service.User.UserLoginService;
import com.cs.project.service.User.UserManageService;

/**
 * Clase implementadora de todos los servicios del usuario
 *
 * @author nan2p
 */
@Service
@Slf4j
public class UserServiceImpl implements UserManageService, UserLoginService, UserPostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    /**
     * Permite comunicarse con la capa de datos para registrar en el servidor al usuario
     * @param name nombre del usuario
     * @param lastName apellido del usuario
     * @param email correo electronico del usuario
     * @param userName nombre de usuario del usuario
     * @param password contraseña del usuario
     * @return true si fue registrado con exito, false si ya existe un usuario registrado con datos unicos
     */
    @Override
    public boolean userRegister(String name, String lastName, String email, String userName, String password, int rol) {
        if (!verifyUserRegister(email, userName)) {
            User user = new User(name, lastName, email, userName, password, rol);
            return userRepository.userRegister(user);
        } else {
            return false;
        }
    }

    /**
     * Permite encontrar al usuario autentificado en el sistema
     * @param session
     * @return datos del usuario logueado
     */
    @Override
    public User findUser(HttpSession session) {
        int value = (int) session.getAttribute("userIdLogged");
        return userRepository.returnUser(value);
    }

    /**
     * Permite actualizar los datos del usuario
     * @param name nombre del usuario
     * @param lastName apellido del usuario
     * @param email correo electronico del usuario
     * @param userName nombre de usuario del usuario
     * @param password contraseña del usuario
     * @param session session creada por el sistema
     * @return true si fue actualizado con exito, false si ya existe un nombre de usuario o email en el sistema
     */
    @Override
    public boolean userUpdate(String name, String lastName, String email, String userName, String password, HttpSession session) {
        int userId = (Integer) session.getAttribute("userIdLogged");
        User user = new User(name, lastName, email, userName, password, 1);
        return userRepository.updateUser(user, userId);
    }

    /**
     * Permite crear la session del usuario dentro del sistema
     * @param email correo electronico del usuario
     * @param password contraseña del usuario 
     * @param session session creada por el sistema
     * @return true si se encontro y crreo la session, false si el usario no existe
     */
    @Override
    public boolean UserAuthenticate(String email, String password, HttpSession session) {
        log.info("SERVICE UserAuthenticate");
        int value = userRepository.findUser(email, password);
        User user = userRepository.returnUser(value);
        if (user != null) {
            session.setAttribute("userIdLogged", value);
            session.setAttribute("userRolLogged", user.getRol());
            return true;
        } else {
            return false;
        }
    }

    /**
     * Permite buscar todos los post hechos por el usuario logueado
     * @param session session creada por el sistema
     * @return Collecion con todos los post del usuario
     */
    @Override
    public List<Post> getPostsByUser(HttpSession session) {
        int value = (Integer) session.getAttribute("userIdLogged");
        return postRepository.getPostsByUser(value);
    }

    /**
     * verifica si el email y el nombre de usuario no existe en el sistema
     * @param email correo electronico del usuario
     * @param userName nomre de usuario del sistema
     * @return true si ya existe uno de los dos campos, false si no existen
     */
    private boolean verifyUserRegister(String email, String userName) {
        return userRepository.findEmail(email) || userRepository.findUserName(userName);
    }
}
