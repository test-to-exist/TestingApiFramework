package simple.testing.framework.ApiTests.response;

import java.io.Serializable;

public class BookingDates implements Serializable {
    private String checkout;
    private String checkin;

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }
}
