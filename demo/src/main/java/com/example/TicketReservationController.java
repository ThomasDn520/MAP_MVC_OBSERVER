public class TicketReservationController {
    private Bus bus;

    public TicketReservationController(Bus bus) {
        this.bus = bus;
    }

    public void reserveSeat(int seatNumber) {
        bus.reserveSeat(seatNumber);
    }

    public void purchaseSeat(int seatNumber) {
        bus.purchaseSeat(seatNumber);
    }
}
