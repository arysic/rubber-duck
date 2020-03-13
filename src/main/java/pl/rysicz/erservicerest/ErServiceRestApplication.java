package pl.rysicz.erservicerest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@ComponentScan(basePackages = {"pl.rysicz.erservicerest.model",
		"pl.rysicz.erservicerest.controller"})
public class ErServiceRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErServiceRestApplication.class, args);
	}

}
