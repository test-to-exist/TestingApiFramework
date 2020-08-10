package simple.testing.framework.ApiTests;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import simple.testing.framework.ApiTests.responses.Booking;
import simple.testing.framework.ApiTests.services.BookingService;

public class BookingApiTests {

    private static BookingService bookingService;

    @Test
    public void initialTest() throws JsonProcessingException {
        ResponseEntity<String> response = bookingService.getBooking(1);
        System.out.println(response.getStatusCode());
        ObjectMapper mapper = new ObjectMapper();
        Booking booking = mapper.readValue(response.getBody(),Booking.class);
        System.out.println(booking.getFirstname());
        assert 1 == 1;
    }

    @Test
    public void createBooking() {
        var response = bookingService.createBooking();
    }

    @BeforeClass
    public static void beforeClass() throws Exception {
        bookingService = new BookingService(new RestTemplateBuilder());
    }
}
