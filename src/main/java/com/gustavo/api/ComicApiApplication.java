package com.gustavo.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class ComicApiApplication {
	
	@Value("${marvel.baseUrl}")
	private String baseUrl;
	
	@Bean
	public WebClient webClient(WebClient.Builder builder) {
		return builder.baseUrl(baseUrl)
		.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
		.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(ComicApiApplication.class, args);
	}

}
