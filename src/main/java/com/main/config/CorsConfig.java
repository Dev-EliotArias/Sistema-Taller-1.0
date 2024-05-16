package com.main.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**") // Define el mapeo de las URLs que quieres permitir CORS
				.allowedOrigins("*") // Define los dominios permitidos
				.allowedMethods("GET", "POST", "PUT", "DELETE"); // Define los métodos HTTP permitidos
	}

}
