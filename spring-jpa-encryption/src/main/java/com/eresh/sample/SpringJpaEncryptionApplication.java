package com.eresh.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class SpringJpaEncryptionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaEncryptionApplication.class, args);
	}

}
