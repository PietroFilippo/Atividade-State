// Estado de pedido pago - aguardando envio
// Permite apenas envio ou cancelamento
public class PagoState implements OrderState {

    private static final String NOME_ESTADO = "Pago";

    @Override
    public void pagar(Order order) {
        throw new IllegalStateException(
                "Pedido já foi pago. Estado atual: " + NOME_ESTADO);
    }

    @Override
    public void enviar(Order order) {
        System.out.println("Preparando envio do pedido " + order.getId() + "...");
        order.setState(new EnviadoState());
        System.out.println("Pedido " + order.getId() + " enviado com sucesso.");
    }

    @Override
    public void entregar(Order order) {
        throw new IllegalStateException(
                "Não é possível entregar um pedido que ainda não foi enviado. Estado atual: " + NOME_ESTADO);
    }

    @Override
    public void cancelar(Order order) {
        System.out.println("Cancelando pedido " + order.getId() + "...");
        order.setState(new CanceladoState());
        System.out.println("Pedido " + order.getId() + " cancelado com sucesso.");
    }

    @Override
    public String getNomeEstado() {
        return NOME_ESTADO;
    }
}
