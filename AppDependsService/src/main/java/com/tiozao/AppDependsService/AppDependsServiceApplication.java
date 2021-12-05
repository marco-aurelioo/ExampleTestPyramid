package com.tiozao.AppDependsService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AppDependsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppDependsServiceApplication.class, args);
	}

}
