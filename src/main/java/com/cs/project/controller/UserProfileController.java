/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cs.project.controller;

import com.cs.project.model.Post;
import com.cs.project.model.User;
import com.cs.project.service.User.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author nan2p
 */
@Controller
public class UserProfileController {

    @Autowired
    UserServiceImpl userService;

    /**
     * Vista principal del perfil del usuario
     * @param model usuario a mostrar en el usuario 
     * @param session session del usuario logueado
     * @return  vista profile.html
     */
    @GetMapping("/profile")
    public String userProfileView(Model model, HttpSession session) {        
        User user = userService.findUser(session);
        List<Post> posts = userService.getPostsByUser(session);
        model.addAttribute("user", user);
        model.addAttribute("posts", posts);
        model.addAttribute("rol", (int) session.getAttribute("userRolLogged"));
        return "profile";
    }

    /**
     * Procesamiento de datos para actualizar el usuario
     * @param allParams parametros del formulario
     * @param redirect mensajes de informacion
     * @param session session creda por el login
     * @return ruta del profile
     */
    @PostMapping("/update-user")
    public String userUpdate(@RequestParam Map<String, String> allParams, RedirectAttributes redirect, HttpSession session) {

        String name = allParams.get("name");
        String lastName = allParams.get("last_name");
        String email = allParams.get("email");
        String userName = allParams.get("username");
        String password = allParams.get("password");
        if (userService.userUpdate(name, lastName, email, userName, password, session)) {
            redirect.addFlashAttribute("message", "Usuario actualizado");
        } else {
            redirect.addFlashAttribute("message", "Nombre de usuario o email ya registrado");
        }
        return "redirect:/profile";
    }
}
