package com.example.Spring.boot.project1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
	@SpringBootApplication = @SpringBootConfiguration + @ComponentScan + @EnableAutoConfiguration
	@ComponentScan searches every Beans that has @Component annotation in the same package.
	Including (@Configuration, @Repository, @Service, @Controller and @RestController)
*/

//@ComponentScan
//@EnableAutoConfiguration
@SpringBootApplication
public class SpringBootProject01Application {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootProject01Application.class, args);
	}
}
