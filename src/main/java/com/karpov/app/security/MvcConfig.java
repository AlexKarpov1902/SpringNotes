package com.karpov.app.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/add-edit-note").setViewName("add-edit-note");
		registry.addViewController("/find-notes").setViewName("find-notes");
		registry.addViewController("/list-notes").setViewName("list-notes");
		registry.addViewController("/login").setViewName("login");
	}

}
