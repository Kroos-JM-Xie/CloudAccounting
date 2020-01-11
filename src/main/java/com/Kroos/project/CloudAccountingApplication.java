package com.Kroos.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@EnableAutoConfiguration
@EnableSwagger2
public class CloudAccountingApplication {
	@RequestMapping("/test")
	public String home() {
		System.out.println(calculate());
		return "Hello World!"+calculate();
	}
	public int calculate(){
		int i=1;
		return (++i)+i++;
	}
	public static void main(String[] args) {
		SpringApplication.run(CloudAccountingApplication.class, args);
	}
}
