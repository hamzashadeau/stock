package com.example.stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class StockApplication {
	public static ConfigurableApplicationContext cntx;
	public static void main(String[] args) {
		 cntx=SpringApplication.run(StockApplication.class, args);
	}
@Bean
public BCryptPasswordEncoder getBCryptPasswordEncoder() {
	return new BCryptPasswordEncoder();
	}
public static ConfigurableApplicationContext getCntx() {
	return cntx;
}

}
