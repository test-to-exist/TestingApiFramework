package simple.testing.framework.ApiTests;


import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import simple.testing.framework.ApiTests.services.BookingService;

public class BookingApiTests {

    private static BookingService bookingService;

    @Test
    public void initialTest() {
        var response = bookingService.getBooking(1);
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
