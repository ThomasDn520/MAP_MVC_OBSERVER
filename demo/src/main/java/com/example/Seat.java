public class Seat {
    private int seatNumber;
    private String status; // "disponível", "reservado", "indisponível"

    public Seat(int seatNumber) {
        this.seatNumber = seatNumber;
        this.status = "disponível";
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public String getStatus() {
        return status;
    }

    public void reserve() {
        this.status = "reservado";
    }

    public void purchase() {
        this.status = "indisponível";
    }
}

