package com.example.microserviceServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MicroServiceServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServiceServerApplication.class, args);
	}

}
