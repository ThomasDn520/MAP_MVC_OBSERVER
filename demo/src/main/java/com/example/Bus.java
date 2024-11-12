import java.util.ArrayList;
import java.util.List;

public class Bus {
    private List<Seat> seats = new ArrayList<>();
    private List<Observer> observers = new ArrayList<>();

    public Bus(int numSeats) {
        for (int i = 1; i <= numSeats; i++) {
            seats.add(new Seat(i));
        }
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void reserveSeat(int seatNumber) {
        Seat seat = seats.get(seatNumber - 1);
        seat.reserve();
        notifyObservers();
    }

    public void purchaseSeat(int seatNumber) {
        Seat seat = seats.get(seatNumber - 1);
        seat.purchase();
        notifyObservers();
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(seats);
        }
    }

    public List<Seat> getSeats() {
        return seats;
    }
}

