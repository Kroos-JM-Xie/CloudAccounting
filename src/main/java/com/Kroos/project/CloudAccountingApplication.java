package com.Kroos.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class CloudAccountingApplication {
	@RequestMapping("/test")
	String home() {
		return "Hello World!";
	}
	public static void main(String[] args) {
		SpringApplication.run(CloudAccountingApplication.class, args);
	}
}
