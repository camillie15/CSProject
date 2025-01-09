package com.cs.project.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador de errores en el sistema
 * @author nan2p
 */
@Controller
public class ErrorController {

    private static final String ERROR_PATH = "/error";
    
    /**
     * Redirigir a una pagina personalizada por los errores encontrados en el sistema (404)
     * @param request request del cliente
     * @return la vista personzalida del error
     */
    @GetMapping(ERROR_PATH)
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute("javax.servlet.error.status_code");
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            if (statusCode == 404) {
                return "error"; // Nombre de la vista personalizada
            }
        }
        return "error";
    }

}
