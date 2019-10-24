package com.shuffle.fulltexo;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = { "com.shuffle.fulltexo.entity" })
@EnableJpaRepositories(basePackages = { "com.shuffle.fulltexo.repository" })
@ComponentScan(basePackages = { "com.shuffle.fulltexo.controller", "com.shuffle.fulltexo.service" })
public class Application {

	public static ApplicationContext applicationContext;

	public static void main(String[] args) {
		Application.applicationContext = SpringApplication.run(Application.class, args);
	}

	@PostConstruct
	private void setTimeZone() {
		TimeZone.setDefault(TimeZone.getTimeZone("BRT"));
	}
}
