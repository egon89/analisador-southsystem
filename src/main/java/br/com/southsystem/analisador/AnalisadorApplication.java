package br.com.southsystem.analisador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AnalisadorApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnalisadorApplication.class, args);
	}

}
