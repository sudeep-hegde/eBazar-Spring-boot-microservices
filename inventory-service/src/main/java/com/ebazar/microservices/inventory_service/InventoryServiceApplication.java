package com.ebazar.microservices.inventory_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * implemented db migration using flyway
 * implemented integration tests using testcontainers (need to check if testcontainers require docker to be installed in local system)
 */
@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

}
