package com.pablodlc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.pablodlc.appspring.interceptor.InterceptorSchedule;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // Método para configurar el manejo de CORS (Cross-Origin Resource Sharing)
    @Override
    public void addCorsMappings(@SuppressWarnings("null") CorsRegistry registry) {
        // Define el patrón de URL para el cual se aplicará la configuración de CORS
        registry.addMapping("/api/**") 
                .allowedOrigins("http://localhost:3000") // Especifica los orígenes permitidos
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Especifica los métodos HTTP permitidos
                .allowedHeaders("*"); // Especifica los encabezados HTTP permitidos
    }

    // Método para agregar interceptores
    @Override
    public void addInterceptors(@SuppressWarnings("null") InterceptorRegistry registry) {
        registry.addInterceptor(new InterceptorSchedule()); // Agrega un interceptor de tipo InterceptorSchedule
    }
}

