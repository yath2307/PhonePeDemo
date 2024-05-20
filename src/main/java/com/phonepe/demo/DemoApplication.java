package com.phonepe.demo;

import com.phonepe.demo.entity.mongo.Customer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;

@SpringBootApplication
public class DemoApplication {

	public static HashMap<String, Customer> customerHashMap = new HashMap<>();

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
