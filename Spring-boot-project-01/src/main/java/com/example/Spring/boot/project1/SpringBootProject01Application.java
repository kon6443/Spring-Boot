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
//@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class})
//@EnableAutoConfiguration(basePackages="com.example.Spring.boot.project1.controllers")
@EnableAutoConfiguration()
public class SpringBootProject01Application {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootProject01Application.class, args);
	}

}
