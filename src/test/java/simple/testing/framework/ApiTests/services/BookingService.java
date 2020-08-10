package simple.testing.framework.ApiTests.services;

import jdk.internal.net.http.common.Utils;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;
import simple.testing.framework.ApiTests.response.BookingResponse;

public class BookingService {

    private final RestTemplate restTemplate;

    public BookingService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void createBooking() {
    }

}
