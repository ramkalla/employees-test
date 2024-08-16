package com.employees.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableJpaRepositories("com.employees.test.*")
@EnableAutoConfiguration
@ComponentScan("com.employees.*")
@Slf4j
@OpenAPIDefinition(info = @Info(title = "Employee APIS", description = "API for Retreving Employee details"))
public class SpringBootAPIsApplication {
	public static Logger log = LoggerFactory.getLogger(SpringBootAPIsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAPIsApplication.class, args);
		log.info("Started..........");
	}
}
// com.employees.test.controller
