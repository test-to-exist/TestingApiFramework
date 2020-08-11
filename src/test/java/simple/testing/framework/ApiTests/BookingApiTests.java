package simple.testing.framework.ApiTests;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import simple.testing.framework.ApiTests.responses.AuthResponse;
import simple.testing.framework.ApiTests.responses.Booking;
import simple.testing.framework.ApiTests.responses.BookingCreated;
import simple.testing.framework.ApiTests.services.AuthService;
import simple.testing.framework.ApiTests.services.BookingService;

public class BookingApiTests {

    private static BookingService bookingService;
    private static AuthService authService;

    @Test
    public void initialTest() throws JsonProcessingException {
        ResponseEntity<String> response = bookingService.getBooking(1);
        System.out.println(response.getStatusCode());
        ObjectMapper mapper = new ObjectMapper();
        Booking booking = mapper.readValue(response.getBody(), Booking.class);
        System.out.println(booking.getFirstname());
        assert response.getStatusCode() == HttpStatus.OK;
    }

    @Test
    public void createBooking() throws JsonProcessingException {

        var response = bookingService.createBooking();
        assert response.getStatusCode() == HttpStatus.OK;
        ObjectMapper mapper = new ObjectMapper();
        BookingCreated bc = mapper.readValue(response.getBody(), BookingCreated.class);
        assert bc.getBooking().getFirstname().equals("Jan");
        assert bc.getBooking().getLastname().equals("Kovalsky");
    }

    @Test
    public void deleteBooking() throws JsonProcessingException {
        ResponseEntity<String> authResponse = authService.getToken("admin", "password123");
        assert authResponse.getStatusCode() == HttpStatus.OK;
        ObjectMapper mapper = new ObjectMapper();
        AuthResponse authResponseObject = mapper.readValue(authResponse.getBody(), AuthResponse.class);
        System.out.println(authResponseObject.getToken());

        var response = bookingService.createBooking();
        assert response.getStatusCode() == HttpStatus.OK;
        BookingCreated bc = mapper.readValue(response.getBody(), BookingCreated.class);

        var delResponse = bookingService.deleteBooking(bc.getBookingid(), authResponseObject.getToken());
        assert delResponse.getStatusCode() == HttpStatus.CREATED;
    }

    @BeforeClass
    public static void beforeClass() throws Exception {
        authService = new AuthService(new RestTemplateBuilder());
        bookingService = new BookingService(new RestTemplateBuilder());
    }
}
