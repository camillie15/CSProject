package com.cs.project.controller;

import com.cs.project.service.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controlador de la vista del login
 *
 * @author Erick Cordova
 */
@Controller
@Slf4j
public class LoginController {

    private UserServiceImpl userService;

    @Autowired
    public LoginController(UserServiceImpl userService) {
        this.userService = userService;
    }

    /**
     * Vista y control del login del usuario
     *
     * @param model mensaje de presnetación
     * @return vista login.hmtl
     */
    @GetMapping("/login")
    public String loginView(Model model) {
        String message = "Ingrese su usuario";
        model.addAttribute("message", message);
        return "login";
    }

    /**
     * Peticon de logueo al sistema
     *
     * @param allParams obtener paametros del formulario
     * @param session
     * @return retorna al dashboard si se encuentra al usuario y regresa al
     * login si no lo encuentra
     */
    @PostMapping("/login-submit")
    public String loginRequest(@RequestParam Map<String, String> allParams, HttpSession session, Model model) {
        String email = allParams.get("email");
        String password = allParams.get("password");
        log.info("User Service");
        if (!userService.UserAuthenticate(email, password, session)) {
            String message = "Usuario no encontrado";
            model.addAttribute("message", message);
            return "/login";
        } else {
            return "redirect:/register";
        }
    }
}
