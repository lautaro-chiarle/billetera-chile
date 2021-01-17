package cl.com.billetera.desafio.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("bearer-jwt",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
                                .in(SecurityScheme.In.HEADER).name("Authorization")))
                .info(new Info().title("Billetera Chile - Desafío")
                        .description("Esta es la API del Desafío, basada en OpenAPI 3.").version("0.0.1")
                        .contact(new Contact().email("chiarlelautaro@gmail.com").name("Lautaro Chiarle")
                                .url("https://www.linkedin.com/in/lautaro-chiarle-12ba2641/?locale=en_US")))
                                .addSecurityItem(
                                    new SecurityRequirement().addList("bearer-jwt", Arrays.asList("read", "write")));
    }
}
