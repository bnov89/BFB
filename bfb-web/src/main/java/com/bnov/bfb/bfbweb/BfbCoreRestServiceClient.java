package com.bnov.bfb.bfbweb;

import com.bnov.bfb.bfbcore.service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BfbCoreRestServiceClient {

    private final String userRestServiceUri;
    private final RestTemplate template;

    @Autowired
    public BfbCoreRestServiceClient(@Value("${bfb.security.core.uri}") String userRestServiceUri,
                                    @Qualifier("bfbCoreClient") RestTemplate template) {
        this.userRestServiceUri = userRestServiceUri;
        this.template = template;
    }


    public User addUser(final String login) {
        return template.getForEntity("/user/login/{login}", User.class, login).getBody();
    }

    public <T> ResponseEntity<T> getForEntity(String resourceAddress, Class<T> responseType, Object... uriVariables) {
        return template.getForEntity(resourceAddress, responseType, uriVariables);
    }


}
