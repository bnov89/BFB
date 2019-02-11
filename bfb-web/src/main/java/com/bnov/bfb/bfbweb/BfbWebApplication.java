package com.bnov.bfb.bfbweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BfbWebApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(BfbWebApplication.class, args);
    }
}
