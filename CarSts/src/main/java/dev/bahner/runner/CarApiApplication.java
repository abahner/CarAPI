package dev.bahner.runner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ComponentScan("dev.bahner")
@EntityScan("dev.bahner.beans")
@EnableJpaRepositories("dev.bahner.repositories")
public class CarApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarApiApplication.class, args);
	}

	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/*").allowedOrigins("http://localhost:4200/").allowedMethods("GET", "PUT", "POST", "PATCH", "DELETE")
                        .allowedHeaders("*").allowCredentials(false).maxAge(3600);
                ;
            }
        };
	}
}
