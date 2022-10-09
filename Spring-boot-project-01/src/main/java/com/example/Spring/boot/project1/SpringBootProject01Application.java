package com.example.Spring.boot.project1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

//@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class})
public class SpringBootProject01Application {
//	@Value("${MONGO_URI}")
//	private static String MONGO_URI;
	public static void main(String[] args) {
//		System.out.println("MONGO_URI: "+MONGO_URI);
		SpringApplication.run(SpringBootProject01Application.class, args);
	}

}
