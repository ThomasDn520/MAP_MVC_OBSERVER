import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CentralPanelTest {

    private CentralPanel centralPanel;
    private Bus bus;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setup() {
        bus = new Bus(5); // Cria um ônibus com 5 assentos
        centralPanel = new CentralPanel(); // Cria o painel central
        bus.addObserver(centralPanel); // Adiciona o painel central como observador

        // Captura a saída do console
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testUpdateWhenSeatsAreAvailable() {
        // Nenhum assento foi reservado ou comprado, todos devem estar disponíveis
        bus.notifyObservers(); // Força a notificação dos observadores

        // Verifica se a saída contém os assentos disponíveis com a cor verde
        String expectedOutput = "\033[32mAssento 1: disponível\033[0m\n" +
                "\033[32mAssento 2: disponível\033[0m\n" +
                "\033[32mAssento 3: disponível\033[0m\n" +
                "\033[32mAssento 4: disponível\033[0m\n" +
                "\033[32mAssento 5: disponível\033[0m\n";

        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testUpdateWhenSeatIsReserved() {
        // Reserva o assento 2
        bus.reserveSeat(2);
        bus.notifyObservers(); // Força a notificação dos observadores

        // Verifica se o assento 2 foi marcado como reservado (amarelo)
        String expectedOutput = "\033[32mAssento 1: disponível\033[0m\n" +
                "\033[33mAssento 2: reservado\033[0m\n" +
                "\033[32mAssento 3: disponível\033[0m\n" +
                "\033[32mAssento 4: disponível\033[0m\n" +
                "\033[32mAssento 5: disponível\033[0m\n";

        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testUpdateWhenSeatIsPurchased() {
        // Compra o assento 4
        bus.purchaseSeat(4);
        bus.notifyObservers(); // Força a notificação dos observadores

        // Verifica se o assento 4 foi marcado como indisponível (vermelho)
        String expectedOutput = "\033[32mAssento 1: disponível\033[0m\n" +
                "\033[32mAssento 2: disponível\033[0m\n" +
                "\033[32mAssento 3: disponível\033[0m\n" +
                "\033[31mAssento 4: indisponível\033[0m\n" +
                "\033[32mAssento 5: disponível\033[0m\n";

        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testUpdateMultipleSeats() {
        // Reserva o assento 2 e compra o assento 4
        bus.reserveSeat(2);
        bus.purchaseSeat(4);
        bus.notifyObservers(); // Força a notificação dos observadores

        // Verifica se os assentos foram atualizados corretamente
        String expectedOutput = "\033[32mAssento 1: disponível\033[0m\n" +
                "\033[33mAssento 2: reservado\033[0m\n" +
                "\033[32mAssento 3: disponível\033[0m\n" +
                "\033[31mAssento 4: indisponível\033[0m\n" +
                "\033[32mAssento 5: disponível\033[0m\n";

        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testUpdateNoSeats() {
        bus.notifyObservers(); // Notifica sem nenhuma reserva ou compra

        // Verifica a saída após a notificação
        String expectedOutput = "\033[32mAssento 1: disponível\033[0m\n" +
                "\033[32mAssento 2: disponível\033[0m\n" +
                "\033[32mAssento 3: disponível\033[0m\n" +
                "\033[32mAssento 4: disponível\033[0m\n" +
                "\033[32mAssento 5: disponível\033[0m\n";
        assertEquals(expectedOutput, outputStream.toString());
    }

}

