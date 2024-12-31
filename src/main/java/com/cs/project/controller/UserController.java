package com.cs.project.controller;

import com.cs.project.service.UserServiceImpl;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Clase controladora de todos las peticiones del usuario
 *
 * @author Erick Cordova
 */
@Controller
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    /**
     * Metodo para la vista principal del registro de usuario
     *
     * @param model envio de mensaje de informacion
     * @return retorna la vista principal del usuario
     */
    @GetMapping("/register")
    public String registerView(Model model) {
        String message = "Inserte sus datos para el registro";
        model.addAttribute("message", message);
        return "register";
    }

    /**
     * Procesamiento de la peticion post del registro del usuario
     *
     * @param allParams recoge todos los campos de los inputs
     * @param model envio de mensaje de error
     * @return redirecciona a la pagina principal si todo el registro salio
     * correcto
     */
    @PostMapping("/submit-register")
    public String registerForm(@RequestParam Map<String, String> allParams, Model model) {

        String name = allParams.get("name");
        String lastName = allParams.get("last_name");
        String email = allParams.get("email");
        String userName = allParams.get("username");
        String password = allParams.get("password");

        boolean flag = userService.userRegister(name, lastName, email, userName, password);

        if (flag) {
            return "redirect:/login";
        } else {
            String message = "Este usuario ya se encuentra registrado";
            model.addAttribute("message", message);
            return "register";
        }

    }
}
