package com.missfresh.spring.transaction.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringTransactionDemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringTransactionDemoApplication.class, args);
		String userServiceClassname = context.getBean("userService").getClass().getName();
		System.out.println("userServiceClassname==="+userServiceClassname);
		String orderServiceclassname = context.getBean("otherService").getClass().getName();
		System.out.println("otherServiceclassname===="+orderServiceclassname);
	}
}
