
public class Main {
    public static void main(String[] args) {
        // Criando o ônibus com 10 assentos
        Bus bus = new Bus(10);

        // Criando o painel central e os quiosques
        CentralPanel centralPanel = new CentralPanel();
        Kiosk kiosk1 = new Kiosk();
        Kiosk kiosk2 = new Kiosk();

        // Registrando os observadores (painel e quiosques) no ônibus
        bus.addObserver(centralPanel);
        bus.addObserver(kiosk1);
        bus.addObserver(kiosk2);

        // Criando o controlador de reservas
        TicketReservationController controller = new TicketReservationController(bus);

        // Exibindo o estado inicial dos assentos
        System.out.println("Estado Inicial dos Assentos:");
        bus.notifyObservers();

        // Reservando assento 3
        System.out.println("\nReservando o assento 3:");
        controller.reserveSeat(3);

        // Comprando assento 5
        System.out.println("\nComprando o assento 5:");
        controller.purchaseSeat(5);

        // Reservando assento 7
        System.out.println("\nReservando o assento 7:");
        controller.reserveSeat(7);

        // Comprando assento 9
        System.out.println("\nComprando o assento 9:");
        controller.purchaseSeat(9);

        // Exibindo o estado final dos assentos
        System.out.println("\nEstado Final dos Assentos:");
        bus.notifyObservers();
    }
}


