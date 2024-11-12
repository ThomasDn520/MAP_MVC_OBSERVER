import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class KioskTest {

    private Kiosk kiosk;
    private Bus bus;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setup() {
        bus = new Bus(5); // Cria um ônibus com 5 assentos
        kiosk = new Kiosk(); // Cria o quiosque
        bus.addObserver(kiosk); // Adiciona o quiosque como observador

        // Captura a saída do console
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testUpdateWhenSeatsAreAvailable() {
        // Nenhum assento foi reservado ou comprado, todos devem estar disponíveis
        bus.notifyObservers(); // Força a notificação dos observadores

        // Verifica se a saída contém os assentos disponíveis
        String expectedOutput = "Quiosque Atualizado:\n" +
                "Assento 1: disponível\n" +
                "Assento 2: disponível\n" +
                "Assento 3: disponível\n" +
                "Assento 4: disponível\n" +
                "Assento 5: disponível\n";

        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testUpdateWhenSeatIsReserved() {
        // Reserva o assento 2
        bus.reserveSeat(2);
        bus.notifyObservers(); // Força a notificação dos observadores

        // Verifica se o assento 2 foi marcado como reservado
        String expectedOutput = "Quiosque Atualizado:\n" +
                "Assento 1: disponível\n" +
                "Assento 2: reservado\n" +
                "Assento 3: disponível\n" +
                "Assento 4: disponível\n" +
                "Assento 5: disponível\n";

        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testUpdateWhenSeatIsPurchased() {
        // Compra o assento 4
        bus.purchaseSeat(4);
        bus.notifyObservers(); // Força a notificação dos observadores

        // Verifica se o assento 4 foi marcado como indisponível
        String expectedOutput = "Quiosque Atualizado:\n" +
                "Assento 1: disponível\n" +
                "Assento 2: disponível\n" +
                "Assento 3: disponível\n" +
                "Assento 4: indisponível\n" +
                "Assento 5: disponível\n";

        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testUpdateMultipleSeats() {
        // Reserva o assento 2 e compra o assento 4
        bus.reserveSeat(2);
        bus.purchaseSeat(4);
        bus.notifyObservers(); // Força a notificação dos observadores

        // Verifica se os assentos foram atualizados corretamente
        String expectedOutput = "Quiosque Atualizado:\n" +
                "Assento 1: disponível\n" +
                "Assento 2: reservado\n" +
                "Assento 3: disponível\n" +
                "Assento 4: indisponível\n" +
                "Assento 5: disponível\n";

        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testUpdateNoSeats() {
        bus.notifyObservers(); // Notifica sem nenhuma reserva ou compra

        // Verifica a saída após a notificação
        String expectedOutput = "Quiosque Atualizado:\n" +
                "Assento 1: disponível\n" +
                "Assento 2: disponível\n" +
                "Assento 3: disponível\n" +
                "Assento 4: disponível\n" +
                "Assento 5: disponível\n";
        assertEquals(expectedOutput, outputStream.toString());
    }

}

