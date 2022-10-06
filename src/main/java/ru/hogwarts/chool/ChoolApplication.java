package ru.hogwarts.chool;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class ChoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChoolApplication.class, args);
	}

}
