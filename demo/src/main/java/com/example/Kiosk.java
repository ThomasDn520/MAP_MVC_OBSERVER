import java.util.List;

public class Kiosk implements Observer {
    @Override
    public void update(List<Seat> seats) {
        System.out.println("Quiosque Atualizado:");
        for (Seat seat : seats) {
            System.out.println("Assento " + seat.getSeatNumber() + ": " + seat.getStatus());
        }
    }
}

