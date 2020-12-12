package com.lb.simpleWarehouse;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Slf4j
@Configuration
public class SwaggerConfiguration {

    @Value("${project.version}")
    private String projectVersion;

    @Bean
    public OpenAPI simpleWarehouseOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Simple Warehouse API")
                        .description("Simple Warehouse application")
                        .version(projectVersion)
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }

    @EventListener(ApplicationReadyEvent.class)
    public void showSwaggerUrl() {
        log.info("Swagger documentation: http://localhost:8080/swagger-ui-custom.html");
    }

}