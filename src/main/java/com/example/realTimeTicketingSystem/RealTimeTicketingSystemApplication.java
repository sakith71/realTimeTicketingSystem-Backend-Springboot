package com.example.realTimeTicketingSystem;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RealTimeTicketingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(RealTimeTicketingSystemApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
