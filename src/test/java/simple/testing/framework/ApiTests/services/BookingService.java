package simple.testing.framework.ApiTests.services;

import java.net.http.HttpClient;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import simple.testing.framework.ApiTests.response.Booking;
import simple.testing.framework.ApiTests.response.BookingResponse;

import java.time.Duration;
import java.util.Collections;


// https://www.mwtestconsultancy.co.uk/booking

@Service
public class BookingService {

    private final RestTemplate restTemplate;

    public BookingService(RestTemplateBuilder restTemplateBuilder) {

        this.restTemplate = restTemplateBuilder.setConnectTimeout(Duration.ofSeconds(30))
                .setReadTimeout(Duration.ofSeconds(30)).build();
    }

    public BookingResponse getBooking(int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        BookingResponse response = restTemplate.getForObject("https://restful-booker.herokuapp.com/booking/" + id,
                BookingResponse.class);
        return response;
    }


    public void createBooking() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        Booking booking = new Booking();
        HttpEntity entity = new HttpEntity<Booking>(booking, headers);
        restTemplate.postForObject("https://restful-booker.herokuapp.com/booking", booking, Booking.class);
    }

}
