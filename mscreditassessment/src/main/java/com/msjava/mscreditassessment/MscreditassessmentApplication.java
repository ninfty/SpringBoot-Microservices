package com.msjava.mscreditassessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MscreditassessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(MscreditassessmentApplication.class, args);
	}

}
