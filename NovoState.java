// Estado inicial de um pedido - pedido criado mas não pago
// Permite apenas pagamento ou cancelamento

public class NovoState implements OrderState {

    private static final String NOME_ESTADO = "Novo";

    @Override
    public void pagar(Order order) {
        System.out.println("Processando pagamento do pedido " + order.getId() + "...");
        order.setState(new PagoState());
        System.out.println("Pedido " + order.getId() + " pago com sucesso.");
    }

    @Override
    public void enviar(Order order) {
        throw new IllegalStateException(
                "Não é possível enviar um pedido que ainda não foi pago. Estado atual: " + NOME_ESTADO);
    }

    @Override
    public void entregar(Order order) {
        throw new IllegalStateException(
                "Não é possível entregar um pedido que ainda não foi pago. Estado atual: " + NOME_ESTADO);
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
