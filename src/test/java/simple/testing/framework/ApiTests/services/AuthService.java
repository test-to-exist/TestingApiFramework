package simple.testing.framework.ApiTests.services;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import simple.testing.framework.ApiTests.payloads.AuthPayload;
import simple.testing.framework.ApiTests.responses.AuthResponse;

import java.time.Duration;
import java.util.Collections;

@Service
public class AuthService {

    private final RestTemplate restTemplate;

    public AuthService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.setConnectTimeout(Duration.ofSeconds(30))
                .setReadTimeout(Duration.ofSeconds(30)).build();
    }

    public ResponseEntity<AuthResponse> getToken(String username, String password) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        AuthPayload payload = new AuthPayload(username, password);
        HttpEntity<AuthPayload> entity = new HttpEntity<>(payload, headers);
        ResponseEntity<AuthResponse> response = restTemplate.exchange("https://restful-booker.herokuapp.com/auth", HttpMethod.POST,
                entity, AuthResponse.class);
        return response;
    }

}
