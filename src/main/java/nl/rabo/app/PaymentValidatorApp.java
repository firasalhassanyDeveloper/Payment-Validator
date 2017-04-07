package nl.rabo.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@SpringBootApplication
public class PaymentValidatorApp {


	public static void main(String []args) {
		SpringApplication.run(PaymentValidatorApp.class, args);
	}
}