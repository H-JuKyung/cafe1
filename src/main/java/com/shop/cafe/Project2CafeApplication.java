package com.shop.cafe;

import org.springframework.boot.SpringApplication; 
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:config/secu.properties") // classpath == resources
public class Project2CafeApplication {

	public static void main(String[] args) {
		SpringApplication.run(Project2CafeApplication.class, args);
	}

}
