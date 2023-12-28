package com.studygroupzs.virtuallibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class VirtualLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(VirtualLibraryApplication.class, args);
	}

}
