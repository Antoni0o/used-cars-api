package com.usedcarsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class UsedCarsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsedCarsApiApplication.class, args);
	}

}
