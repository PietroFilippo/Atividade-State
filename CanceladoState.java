// Estado de pedido cancelado - ciclo de vida encerrado
//Não permite mais transições de estado

public class CanceladoState implements OrderState {

    private static final String NOME_ESTADO = "Cancelado";

    @Override
    public void pagar(Order order) {
        throw new IllegalStateException(
                "Não é possível pagar um pedido cancelado. Estado atual: " + NOME_ESTADO);
    }

    @Override
    public void enviar(Order order) {
        throw new IllegalStateException(
                "Não é possível enviar um pedido cancelado. Estado atual: " + NOME_ESTADO);
    }

    @Override
    public void entregar(Order order) {
        throw new IllegalStateException(
                "Não é possível entregar um pedido cancelado. Estado atual: " + NOME_ESTADO);
    }

    @Override
    public void cancelar(Order order) {
        throw new IllegalStateException(
                "Pedido já foi cancelado. Estado atual: " + NOME_ESTADO);
    }

    @Override
    public String getNomeEstado() {
        return NOME_ESTADO;
    }
}
