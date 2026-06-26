package com.gameup.ticket_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Ticket Service API")
                        .version("1.0")
                        .description("Documentación del microservicio de tickets de soporte - GameUp"));
    }

    @Bean
    public GroupedOpenApi ticketsApi() {
        return GroupedOpenApi.builder()
                .group("tickets")
                .pathsToMatch("/api/tickets/**")
                .build();
    }
}