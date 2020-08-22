package simple.testing.framework.common;

public class BookingDates {
    private String checkout;
    private String checkin;

    public BookingDates() {
    }

    public BookingDates(String checkin, String checkout) {
        this.checkout = checkout;
        this.checkin = checkin;
    }

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
