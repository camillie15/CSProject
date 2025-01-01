package com.cs.project.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuracion de sessions web
 *
 * @author Erick Cordova
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    List<String> exclude = new ArrayList<>(List.of("/login", "/register", "/login-submit", "/submit-register", "/js/**", "/styles/**"));

    @Autowired
    private SessionInterceptor sessionInterceptor;

    /**
     * Configuracion para redirigir a una ruta segun las session creada
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterceptor).addPathPatterns("/**").excludePathPatterns(exclude);
    }

}
