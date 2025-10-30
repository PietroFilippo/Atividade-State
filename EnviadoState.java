// Estado de pedido enviado - em trânsito para o cliente
// Permite apenas entrega ou cancelamento

public class EnviadoState implements OrderState {

    private static final String NOME_ESTADO = "Enviado";

    @Override
    public void pagar(Order order) {
        throw new IllegalStateException(
                "Pedido já foi pago. Estado atual: " + NOME_ESTADO);
    }

    @Override
    public void enviar(Order order) {
        throw new IllegalStateException(
                "Pedido já foi enviado. Estado atual: " + NOME_ESTADO);
    }

    @Override
    public void entregar(Order order) {
        System.out.println("Confirmando entrega do pedido " + order.getId() + "...");
        order.setState(new EntregueState());
        System.out.println("Pedido " + order.getId() + " entregue com sucesso.");
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
