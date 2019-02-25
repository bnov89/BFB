package com.bnov.bfb.bfbweb.configuration;

import com.bnov.bfb.bfbweb.BfbCoreRestServiceClient;
import com.bnov.bfb.bfbweb.interceptor.RequestResponseLoggingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Configuration
@EnableConfigurationProperties(CoreRestServiceClientProperties.class)
public class RestServiceClientConfiguration {

    private final CoreRestServiceClientProperties properties;

    @Autowired
    public RestServiceClientConfiguration(CoreRestServiceClientProperties properties) {
        this.properties = properties;
    }

    @Bean
    public RestTemplate bfbCoreClient() {
        return new RestTemplateBuilder()
                .requestFactory(() -> new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()))
                .interceptors(Collections.singletonList(new RequestResponseLoggingInterceptor()))
                .basicAuthorization(properties.getUser(), properties.getPassword())
                .rootUri(properties.getUri())
                .build();
    }

    @Bean
    public BfbCoreRestServiceClient bfbCoreRestServiceClient() {
        return new BfbCoreRestServiceClient(bfbCoreClient());
    }
}
