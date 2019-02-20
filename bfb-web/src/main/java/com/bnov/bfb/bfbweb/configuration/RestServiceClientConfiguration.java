package com.bnov.bfb.bfbweb.configuration;

import com.bnov.bfb.bfbweb.interceptor.RequestResponseLoggingInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class RestServiceClientConfiguration {
    @Bean
    public RestTemplate bfbCoreClient(@Value("${bfb.security.core.rest.login}") String restServicesUser,
                                      @Value("${bfb.security.core.rest.password}") String restServicesPassword,
                                      @Value("${bfb.security.core.uri}") String serviceUri) {
        return new RestTemplateBuilder()
                .requestFactory(() -> new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()))
                .interceptors(Collections.singletonList(new RequestResponseLoggingInterceptor()))
                .basicAuthorization(restServicesUser, restServicesPassword)
                .rootUri(serviceUri)
                .build();
    }
}
