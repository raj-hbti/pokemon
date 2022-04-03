package com.myapp.pokemon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class PokemonApplication {
	public static void main(String[] args) {
		SpringApplication.run(PokemonApplication.class, args);
	}
}
