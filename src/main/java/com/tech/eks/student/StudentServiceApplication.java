package com.tech.eks.student;

import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@ComponentScan({ "com.tech.eks.student.controller", "com.tech.eks.student.service", "com.tech.eks.student.global" ,"com.tech.eks.student.config"})
@EntityScan("com.tech.eks.student.entity")
@EnableJpaRepositories("com.tech.eks.student.repository")
@OpenAPIDefinition(info = @Info(description = "student-service api "))
@Slf4j
public class StudentServiceApplication {

	public static void main(String[] args) throws InterruptedException {
		long startTime=System.currentTimeMillis();
		log.info("************ StudentServiceApplication strts at {} *******************"+LocalDateTime.now());
		//Thread.sleep(20000);// Intentionally wrote this to see system delay
		SpringApplication.run(StudentServiceApplication.class, args);
		long endTime=System.currentTimeMillis();
		
		log.info("************ StudentServiceApplication total time taken for startup in milli seconds {} ******************* -> "+(endTime-startTime)/1000);

	}
	
}
