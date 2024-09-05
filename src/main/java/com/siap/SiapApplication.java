package com.siap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@ComponentScan(basePackages = "com.siap")
public class SiapApplication {

	public static void main(String[] args) {
		SpringApplication.run(SiapApplication.class, args);
	}
}
