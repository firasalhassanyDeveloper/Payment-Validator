package nl.rabo.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;



@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages = {"nl.rabo.app.service"})
public class PaymentValidatorApp {
	
	public static void main(String[] args) {
		SpringApplication.run(PaymentValidatorApp.class, args);
	}
}