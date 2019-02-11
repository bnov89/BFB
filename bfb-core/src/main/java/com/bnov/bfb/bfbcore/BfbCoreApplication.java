package com.bnov.bfb.bfbcore;

import com.bnov.bfb.bfbcore.dao.*;
import com.bnov.bfb.bfbcore.model.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class BfbCoreApplication  {
	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(BfbCoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		userRepository.save(new User("bartek", "nowak"));
		return args -> {
			System.out.println("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}

		};

	}

}

