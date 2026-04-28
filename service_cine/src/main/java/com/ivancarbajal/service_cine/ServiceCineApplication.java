package com.ivancarbajal.service_cine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ServiceCineApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceCineApplication.class, args);
	}

}
