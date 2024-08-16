package com.employees.test.config;

import java.util.List;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {

	@Value("${employee.openapi.dev-url}")
	private String devUrl;

	@Value("${employee.openapi.prod-url}")
	private String prodUrl;

	@Bean
	public OpenAPI myOpenAPI() {
		Server devServer = new Server();
		devServer.setUrl(devUrl);
		devServer.setDescription("Server URL in Development environment");

		Server prodServer = new Server();
		prodServer.setUrl(prodUrl);
		prodServer.setDescription("Server URL in Production environment");

		Info info = new Info().title("Employee Microservices").version("1.0")
				.description("This API exposes endpoints to Employee retrival data");

		return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
	}
	
	@Bean
	public GroupedOpenApi repairOrderWorkInProcessApi() {
		return GroupedOpenApi.builder().group("Employees").packagesToScan("com.employees.test").build();
	}
}