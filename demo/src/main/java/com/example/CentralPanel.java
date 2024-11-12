import java.util.List;

public class CentralPanel implements Observer {
    @Override
    public void update(List<Seat> seats) {
        System.out.println("Painel Central Atualizado:");

        for (Seat seat : seats) {
            String colorCode;
            switch (seat.getStatus()) {
                case "disponível":
                    colorCode = "\033[32m"; // Verde para disponível
                    break;
                case "reservado":
                    colorCode = "\033[33m"; // Amarelo para reservado
                    break;
                case "indisponível":
                    colorCode = "\033[31m"; // Vermelho para indisponível
                    break;
                default:
                    colorCode = "\033[0m"; // Reseta a cor por padrão
                    break;
            }

            System.out.println(colorCode + "Assento " + seat.getSeatNumber() + ": " + seat.getStatus() + "\033[0m");
        }
    }
}
