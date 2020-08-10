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
import java.util.Collections;


// https://www.mwtestconsultancy.co.uk/booking

@Service
public class BookingService {

    private final RestTemplate restTemplate;

    public BookingService(RestTemplateBuilder restTemplateBuilder) {

        this.restTemplate = restTemplateBuilder.setConnectTimeout(Duration.ofSeconds(30))
                .setReadTimeout(Duration.ofSeconds(30)).build();
    }

    public ResponseEntity<BookingCreated> getBooking(int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        ResponseEntity<BookingCreated> response = restTemplate.exchange(
                "https://restful-booker.herokuapp.com/booking/" + id, HttpMethod.GET, HttpEntity.EMPTY,
                BookingCreated.class);
        return response;
    }

    public ResponseEntity<Booking> createBooking() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        Booking booking = new Booking("Jan", "Kovalsky", 99, true, new BookingDates(
                LocalDateTime.of(2020, 1, 1, 12, 0)
                        .format(DateTimeFormatter.BASIC_ISO_DATE),
                LocalDateTime.of(2020, 1, 7, 12, 0)
                        .format(DateTimeFormatter.BASIC_ISO_DATE)));
        HttpEntity entity = new HttpEntity<Booking>(booking, headers);
        ResponseEntity<Booking> response = restTemplate.exchange("https://restful-booker.herokuapp.com/booking",
                HttpMethod.POST, entity, Booking.class);
        return response;
    }

}
