package com.bnov.bfb.bfbweb;

import com.bnov.bfb.bfbcore.service.model.User;
import com.bnov.bfb.bfbweb.configuration.RestServiceClientConfiguration;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;

@RunWith(SpringRunner.class)
@RestClientTest(BfbCoreRestServiceClient.class)
@SpringBootTest(properties = {
        "bfb.security.core.rest.login=testLogin",
        "bfb.security.core.rest.password=testPass",
        "bfb.security.core.uri=http://testURI"
})
@ContextConfiguration(classes = {RestServiceClientConfiguration.class})
public class BfbCoreRestServiceClientTest {

    private static final String LOGIN = "testLogin";
    private static final String PASSWORD = "testPass";
    private static final String URI = "http://testURI";

    private BfbCoreRestServiceClient client;
    private MockRestServiceServer server;

    @Qualifier("bfbCoreClient")
    @Autowired
    private RestTemplate template;
    private ObjectMapper objectMapper = new ObjectMapper();


    @Before
    public void setUp() {
        server = MockRestServiceServer.createServer(template);
        client = new BfbCoreRestServiceClient(template);
        // FIXME WORKAROUND till https://github.com/spring-projects/spring-framework/commit/541ee13934cf8c1dfcbcfadce2bb6299bd25900c is not merged
        final ClientHttpRequestFactory requestFactory = (ClientHttpRequestFactory) ReflectionTestUtils.getField(template, "requestFactory");
        template.setRequestFactory(new BufferingClientHttpRequestFactory(requestFactory));
    }

    @Test
    public void givenConfiguration_requestSentToCorrectUrlAndReturnCorrectUser() throws JsonProcessingException {
        User user = new User(LOGIN, PASSWORD);
        server.expect(requestTo(URI + "/user/login/testLogin"))
                .andRespond(MockRestResponseCreators.withStatus(HttpStatus.OK)
                        .body(objectMapper.writeValueAsBytes(user))
                        .contentType(MediaType.APPLICATION_JSON_UTF8));
        User result = client.findByLogin("testLogin");
        assertThat(result).isNotNull();
        assertThat(result.getLogin()).isEqualTo(LOGIN);
        assertThat(result.getPassword()).isEqualTo(PASSWORD);
    }
}