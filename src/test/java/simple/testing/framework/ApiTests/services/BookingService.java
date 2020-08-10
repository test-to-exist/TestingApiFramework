package simple.testing.framework.ApiTests.services;

import jdk.internal.net.http.common.Utils;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import simple.testing.framework.ApiTests.response.Booking;
import simple.testing.framework.ApiTests.response.BookingResponse;

import java.util.Collections;


// https://www.mwtestconsultancy.co.uk/booking


public class BookingService {

private final RestTemplate restTemplate ;

    public BookingService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void createBooking() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        Booking booking = new Booking();
        HttpEntity entity = new HttpEntity<Booking>(booking, headers);
        restTemplate.postForObject("https://www.mwtestconsultancy.co.uk/booking", booking, Booking.class);
    }

}
