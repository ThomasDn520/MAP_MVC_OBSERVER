import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BusTest {

    private Bus bus;
    private FakeObserver centralPanel;
    private FakeObserver kiosk;

    @BeforeEach
    public void setup() {
        bus = new Bus(5); // Cria um ônibus com 5 assentos
        centralPanel = new FakeObserver(); // Instancia um observador falso
        kiosk = new FakeObserver(); // Instancia outro observador falso
        bus.addObserver(centralPanel); // Adiciona o painel central como observador
        bus.addObserver(kiosk); // Adiciona o quiosque como observador
    }

    @Test
    public void testInitialSeatStatus() {
        // Verifica que os assentos estão inicialmente disponíveis
        List<Seat> seats = bus.getSeats();
        assertEquals("disponível", seats.get(0).getStatus());
        assertEquals("disponível", seats.get(1).getStatus());
        assertEquals("disponível", seats.get(2).getStatus());
        assertEquals("disponível", seats.get(3).getStatus());
        assertEquals("disponível", seats.get(4).getStatus());
    }

    @Test
    public void testReserveSeat() {
        bus.reserveSeat(2); // Reserva o assento 2

        // Verifica se o assento 2 foi reservado
        assertEquals("reservado", bus.getSeats().get(1).getStatus());

        // Verifica se os observadores foram notificados
        assertTrue(centralPanel.isUpdated());
        assertTrue(kiosk.isUpdated());
    }

    @Test
    public void testPurchaseSeat() {
        bus.purchaseSeat(4); // Compra o assento 4

        // Verifica se o assento 4 foi comprado (indisponível)
        assertEquals("indisponível", bus.getSeats().get(3).getStatus());

        // Verifica se os observadores foram notificados
        assertTrue(centralPanel.isUpdated());
        assertTrue(kiosk.isUpdated());
    }

    @Test
    public void testNotifyObserversOnReservation() {
        bus.reserveSeat(1); // Reserva o assento 1

        // Verifica se os observadores foram notificados após a reserva
        assertTrue(centralPanel.isUpdated());
        assertTrue(kiosk.isUpdated());
    }

    @Test
    public void testNotifyObserversOnPurchase() {
        bus.purchaseSeat(3); // Compra o assento 3

        // Verifica se os observadores foram notificados após a compra
        assertTrue(centralPanel.isUpdated());
        assertTrue(kiosk.isUpdated());
    }

    @Test
    public void testRemoveObserver() {
        bus.removeObserver(centralPanel); // Remove o painel central como observador

        bus.reserveSeat(2); // Reserva o assento 2

        // Verifica que o painel central não foi notificado após a remoção
        assertFalse(centralPanel.isUpdated());
        // Verifica que o quiosque ainda foi notificado
        assertTrue(kiosk.isUpdated());
    }

    @Test
    public void testReserveInvalidSeat() {
        assertThrows(IndexOutOfBoundsException.class, () -> bus.reserveSeat(10)); // Testa se a exceção é lançada ao tentar reservar um assento inválido
    }

    // Classe FakeObserver para simular o comportamento de um observador
    private static class FakeObserver implements Observer {

        private boolean updated = false;

        @Override
        public void update(List<Seat> seats) {
            this.updated = true; // Marca que o observador foi atualizado
        }

        public boolean isUpdated() {
            return updated; // Retorna se o observador foi notificado
        }

        public void reset() {
            this.updated = false; // Reseta o estado do observador para falso
        }
    }
}
