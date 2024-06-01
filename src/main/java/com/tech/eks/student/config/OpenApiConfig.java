/*
 * package com.tech.eks.student.config;
 * 
 * import io.swagger.v3.oas.models.OpenAPI; import
 * io.swagger.v3.oas.models.info.Info; import lombok.extern.slf4j.Slf4j;
 * 
 * import org.springdoc.core.GroupedOpenApi; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration;
 * 
 * @Configuration
 * 
 * @Slf4j public class OpenApiConfig {
 * 
 * public OpenApiConfig() { log.info("OpenApiConfig Constructor ****** "); }
 * 
 * @Bean public OpenAPI customOpenAPI() { return new OpenAPI() .info(new Info()
 * .title("student-service") .version("1.0")
 * .description("This is a sample Spring Boot RESTful service using OpenAPI 3.")
 * ); }
 * 
 * @Bean public GroupedOpenApi publicApi() { return GroupedOpenApi.builder()
 * .group("public") .pathsToMatch("/**") .build(); } }
 */