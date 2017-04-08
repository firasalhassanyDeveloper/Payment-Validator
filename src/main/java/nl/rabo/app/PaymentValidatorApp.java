package nl.rabo.app;

import java.io.File;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import nl.rabo.app.service.PaymentValidatorService;
import nl.rabo.app.service.ValidationError;



@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages = {"nl.rabo.app"})
public class PaymentValidatorApp {
	
	public static void main(String[] args) {
		SpringApplication.run(PaymentValidatorApp.class, args);
		
	}
}