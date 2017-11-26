package com.intellect.UserDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages="com")
@SpringBootApplication
public class UserDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserDemoApplication.class, args);
	}
	

}
