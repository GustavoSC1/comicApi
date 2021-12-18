package com.gustavo.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
// Com esta anotação, habilitamos a varredura de componentes para interfaces que declaram que são clientes Feign.
@EnableFeignClients
public class ComicApiApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ComicApiApplication.class, args);
	}

}
