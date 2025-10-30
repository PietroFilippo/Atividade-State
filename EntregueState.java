// Estado final de pedido entregue - ciclo de vida concluído
//Não permite mais transições de estado

public class EntregueState implements OrderState {

    private static final String NOME_ESTADO = "Entregue";

    @Override
    public void pagar(Order order) {
        throw new IllegalStateException(
                "Pedido já foi entregue. Estado atual: " + NOME_ESTADO);
    }

    @Override
    public void enviar(Order order) {
        throw new IllegalStateException(
                "Pedido já foi entregue. Estado atual: " + NOME_ESTADO);
    }

    @Override
    public void entregar(Order order) {
        throw new IllegalStateException(
                "Pedido já foi entregue. Estado atual: " + NOME_ESTADO);
    }

    @Override
    public void cancelar(Order order) {
        throw new IllegalStateException(
                "Não é possível cancelar um pedido já entregue. Estado atual: " + NOME_ESTADO);
    }

    @Override
    public String getNomeEstado() {
        return NOME_ESTADO;
    }
}
