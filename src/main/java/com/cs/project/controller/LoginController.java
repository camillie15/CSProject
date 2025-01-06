package com.cs.project.controller;

import com.cs.project.service.User.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controlador de la vista del login
 *
 * @author Erick Cordova
 */
@Controller
@Slf4j
public class LoginController {

    private final UserServiceImpl userService;

    @Autowired
    public LoginController(UserServiceImpl userService) {
        this.userService = userService;
    }

    /**
     * Vista y control del login del usuario
     *
     * @param model mensaje de presnetación
     * @return vista login si no hay una session creada, path= home si hay una session creada
     */
    @GetMapping("/login")
    public String loginView(HttpSession session) {
        if(session.getAttribute("userIdLogged") != null){
            return "redirect:/home";
        }
        return "login";
    }

    /**
     * Peticon de logueo al sistema
     *
     * @param allParams obtener paametros del formulario
     * @param session
     * @param model
     * @return retorna al dashboard si se encuentra al usuario y regresa al
     * login si no lo encuentra
     */
    @PostMapping("/login-submit")
    public String loginRequest(@RequestParam Map<String, String> allParams, HttpSession session, RedirectAttributes redirect) {
        String email = allParams.get("email");
        String password = allParams.get("password");
        log.info("Login submit");
        if (!userService.UserAuthenticate(email, password, session)) {
            String message = "Usuario no encontrado";
            redirect.addFlashAttribute("message", message);
            return "redirect:/login";
        } else {
            return "redirect:/home";
        }
    }
    
    
    /**
     * 
     * @param session
     * @return retorna a la ruta del login
     */
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("userIdLogged");
        return "redirect:/login";
    }
}
