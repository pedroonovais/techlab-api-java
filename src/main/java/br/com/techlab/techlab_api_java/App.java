package br.com.techlab.techlab_api_java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableCaching
@OpenAPIDefinition(
	info = @Info(
		title = "API Tech Lab",
		version = "v1",
		description = "API do sistema de gerenciamento de pátios da Tech Lab",
		contact = @Contact(
			name = "Pedro Novais",
			email = "phenrique101007@gmail.com",
			url = "https://github.com/pedroonovais"
		)
	)
)
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
