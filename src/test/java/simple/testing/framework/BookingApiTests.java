package simple.testing.framework;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import simple.testing.framework.responses.AuthResponse;
import simple.testing.framework.responses.Booking;
import simple.testing.framework.responses.BookingCreated;
import simple.testing.framework.services.AuthService;
import simple.testing.framework.services.BookingService;

public class BookingApiTests {

    private static BookingService bookingService;
    private static AuthService authService;

    @Test
    public void getBooking() throws JsonProcessingException {
        ResponseEntity<String> response = bookingService.getBooking(1);
        assert response.getStatusCode() == HttpStatus.OK;
    }

    @Test
    public void createBooking() throws JsonProcessingException {
        ResponseEntity<String> response = bookingService.createBooking();
        assert response.getStatusCode() == HttpStatus.OK;
        ObjectMapper mapper = new ObjectMapper();
        BookingCreated bc = mapper.readValue(response.getBody(), BookingCreated.class);
        assert bc.getBooking().getFirstname().equals("Jan");
        assert bc.getBooking().getLastname().equals("Kovalsky");
        ResponseEntity<String> getResponse = bookingService.getBooking(bc.getBookingid());
        assert getResponse.getStatusCode() == HttpStatus.OK;
        Booking booking = mapper.readValue(getResponse.getBody(),Booking.class);
        assert booking.getFirstname().equals("Jan");
        assert booking.getLastname().equals("Kovalsky");
    }

    @Test
    public void deleteBooking() throws JsonProcessingException {
        ResponseEntity<String> authResponse = authService.getToken();
        assert authResponse.getStatusCode() == HttpStatus.OK;
        ObjectMapper mapper = new ObjectMapper();
        AuthResponse authResponseObj = mapper.readValue(authResponse.getBody(), AuthResponse.class);
        System.out.println(authResponseObj.getToken());

        ResponseEntity<String> response = bookingService.createBooking();
        assert response.getStatusCode() == HttpStatus.OK;
        BookingCreated bc = mapper.readValue(response.getBody(), BookingCreated.class);

        ResponseEntity<String> deleteResponse = bookingService.deleteBooking(bc.getBookingid(),
                authResponseObj.getToken());
        assert deleteResponse.getStatusCode() == HttpStatus.CREATED;
    }

    @BeforeClass
    public static void beforeClass() {
        authService = new AuthService(new RestTemplateBuilder());
        bookingService = new BookingService(new RestTemplateBuilder());
    }
}
