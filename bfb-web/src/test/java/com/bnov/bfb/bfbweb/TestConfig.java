package com.bnov.bfb.bfbweb;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@TestConfiguration
public class TestConfig {
    @Bean
    public RestTemplate testBfbCoreClient() {
        return new RestTemplateBuilder()
                .basicAuthorization("testUser", "testPassword")
                .build();
    }
}
