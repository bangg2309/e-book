package edu.vn.hcmuaf.ebook;

import edu.vn.hcmuaf.ebook.service.EmailService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(EBookApplication.class, args);
	}
}
