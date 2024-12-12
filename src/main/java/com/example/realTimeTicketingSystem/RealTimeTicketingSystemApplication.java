/**
 * The main application class for the Real-Time Ticketing System.
 * This class is the entry point for the Spring Boot application.
 *
 * @author [Sakith Umagiliya]
 * @version 1.0
 * @since 2024-11-20
 */
package com.example.realTimeTicketingSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * The main application class for the Real-Time Ticketing System.
 * This class is annotated with @SpringBootApplication to enable auto-configuration
 * and component scanning.
 */
@SpringBootApplication
public class RealTimeTicketingSystemApplication {

	/**
	 * The main entry point for the application.
	 * This method is called when the application is started.
	 *
	 * @param args The command-line arguments passed to the application
	 */
	public static void main(String[] args) {
		SpringApplication.run(RealTimeTicketingSystemApplication.class, args);
	}
}
