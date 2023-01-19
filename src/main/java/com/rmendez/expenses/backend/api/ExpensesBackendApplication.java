package com.rmendez.expenses.backend.api;

import com.rmendez.expenses.backend.api.entities.User;
import com.rmendez.expenses.backend.api.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ExpensesBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpensesBackendApplication.class, args);
	}


}
