package com.show.dachen.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringWebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**") // 開放所有 API
				.allowedOrigins("http://localhost:5173") // Vue 的開發伺服器
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS").allowedHeaders("*"); // 允許所有 Header
	}

}
