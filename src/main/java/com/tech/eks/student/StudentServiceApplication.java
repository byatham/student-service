package com.tech.eks.student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@ComponentScan({ "com.tech.eks.student.controller", "com.tech.eks.student.service", "com.tech.eks.student.global" ,"com.tech.eks.student.config"})
@EntityScan("com.tech.eks.student.entity")
@EnableJpaRepositories("com.tech.eks.student.repository")
@OpenAPIDefinition(info = @Info(description = "student-service api "))
public class StudentServiceApplication {

	public static void main(String[] args) {
		System.out.println("************ StudentServiceApplication strts &&&& *******************");
		SpringApplication.run(StudentServiceApplication.class, args);
		System.out.println("************ StudentServiceApplication ends {} *******************");

	}
}
