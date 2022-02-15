package com.tdsi.spring_draft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

//@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@SpringBootApplication
@PropertySource(value = {"classpath:config.properties","classpath:application.yml","file:config.properties","https/loomo.com/prod/app.properties"},ignoreResourceNotFound = true)
public class SpringDraftApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDraftApplication.class, args);
	}

}
