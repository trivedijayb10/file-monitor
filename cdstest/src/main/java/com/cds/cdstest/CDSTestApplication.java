package com.cds.cdstest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CDSTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(CDSTestApplication.class, args);
	}

}
