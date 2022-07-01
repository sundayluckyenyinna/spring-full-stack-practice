package com.fullstack.fullstack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FullstackApplication {

	public static void main(String[] args) {
		SpringApplication.run(FullstackApplication.class, args);
	}

	// A bean to initialize on startup of the application context and get all the bean definition names in the context.
	// @Bean
	// public String[] getAllBeanDefinitionNames( ApplicationContext applicationContext ){
	// 	// get all the bean names in an array
	// 	String[] beanNames = applicationContext.getBeanDefinitionNames();
	// 	for(String beanName : beanNames ){
	// 		System.out.println( beanName );
	// 	}
	// 	return beanNames;
	// }
}
