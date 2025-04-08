package edu.vedev404.dosecerta;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(servers = { @Server(url = "/", description = "Default Server url")})
public class DosecertaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DosecertaApplication.class, args);
	}

}
