package com.tavara.clientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PanaderiaClientesApplication {

	public static void main(String[] args) {
		SpringApplication.run(PanaderiaClientesApplication.class, args);
	}

}
