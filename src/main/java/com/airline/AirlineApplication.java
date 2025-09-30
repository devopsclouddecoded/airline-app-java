package com.airline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Main Spring Boot application class for the Airline Management System.
 * This class serves as the entry point for the application.
 * 
 * The application provides a RESTful API for managing airline flights with
 * endpoints for creating, reading, updating, and deleting flight information.
 */
@SpringBootApplication
public class AirlineApplication {

	/**
	 * Main method that starts the Spring Boot application.
	 * 
	 * @param args Command line arguments passed to the application
	 */
	public static void main(String[] args) {
		SpringApplication.run(AirlineApplication.class, args);
	}

}
