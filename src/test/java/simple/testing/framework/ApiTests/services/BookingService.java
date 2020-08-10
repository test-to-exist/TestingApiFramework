package simple.testing.framework.ApiTests.services;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import simple.testing.framework.ApiTests.responses.Booking;
import simple.testing.framework.ApiTests.responses.BookingDates;
import simple.testing.framework.ApiTests.responses.BookingCreated;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Service
public class BookingService {

    private final RestTemplate restTemplate;

    public BookingService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.setConnectTimeout(Duration.ofSeconds(30))
                .setReadTimeout(Duration.ofSeconds(30)).build();
    }

    public ResponseEntity<String> getBooking(int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange("https://restful-booker.herokuapp.com/booking/" + id,
                HttpMethod.GET, entity, String.class);
        return response;
    }

    public ResponseEntity<String> createBooking() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        Booking booking = new Booking("Jan", "Kovalsky", 99, true, new BookingDates(
                LocalDateTime.of(2020, 1, 1, 12, 0)
                        .format(DateTimeFormatter.BASIC_ISO_DATE),
                LocalDateTime.of(2020, 1, 7, 12, 0)
                        .format(DateTimeFormatter.BASIC_ISO_DATE)));

        HttpEntity entity = new HttpEntity<>(booking, headers);
        ResponseEntity<String> response = restTemplate.exchange("https://restful-booker.herokuapp.com/booking",
                HttpMethod.POST, entity, String.class);
        return response;
    }

}
