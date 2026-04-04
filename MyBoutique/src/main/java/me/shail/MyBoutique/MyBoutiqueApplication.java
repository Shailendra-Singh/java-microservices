package me.shail.MyBoutique;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MyBoutiqueApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyBoutiqueApplication.class, args);
	}

	@GetMapping("/")
	public String welcome() {
		return "<h1>Java Microservices Practice</h1><p>The service is up and running on Java 25!</p>";
	}
}
