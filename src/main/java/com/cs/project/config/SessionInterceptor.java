
package com.cs.project.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * Componente de configuracion para WebConfig
 * @author Erick Cordova
 */
@Component
public class SessionInterceptor implements HandlerInterceptor{
    
    /**
     * Compara si existe una session creada por el usario o no
     * @param request
     * @param response
     * @param handler
     * @return Devuelve true si existe la session creada por el usuario o false si no, y redirige a una pagina
     * @throws Exception 
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute("userIdLogged") == null){
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
    
}
