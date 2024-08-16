package com.employees.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorAppConfig implements WebMvcConfigurer {

	@Autowired
	Interceptor interceptor;

	private static final String PATHS = "/api/**";

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptor).addPathPatterns(PATHS).excludePathPatterns("/swagger-ui.html",
				"/v2/api-docs", "/swagger-resources/**", "/webjars/**");

	}
}
