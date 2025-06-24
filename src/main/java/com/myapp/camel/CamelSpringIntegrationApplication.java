package com.myapp.camel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
//@ImportResource("classpath:camel1/route1.xml")
public class CamelSpringIntegrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamelSpringIntegrationApplication.class, args);
	}

}
