import java.time.LocalDateTime;
import java.util.Objects;

// Classe que representa um pedido no sistema de e-commerce
// Implementa o padrão State para gerenciar o ciclo de vida do pedido

public class Order {

    private final String id;
    private final String cliente;
    private final double valor;
    private final LocalDateTime dataCriacao;
    private OrderState estado;


    //Construtor para criar um novo pedido
    public Order(String id, String cliente, double valor) {
        this.id = Objects.requireNonNull(id, "ID do pedido não pode ser nulo");
        this.cliente = Objects.requireNonNull(cliente, "Nome do cliente não pode ser nulo");
        this.valor = valor;
        this.dataCriacao = LocalDateTime.now();
        this.estado = new NovoState();
    }

    //Processa o pagamento do pedido
    public void pagar() {
        estado.pagar(this);
    }

    //Envia o pedido para o cliente
    public void enviar() {
        estado.enviar(this);
    }

    //Confirma a entrega do pedido
    public void entregar() {
        estado.entregar(this);
    }

    //Cancela o pedido
    public void cancelar() {
        estado.cancelar(this);
    }

    //Altera o estado do pedido
    //Método interno usado pelos estados para realizar transições
    void setState(OrderState novoEstado) {
        this.estado = Objects.requireNonNull(novoEstado, "Estado não pode ser nulo");
    }

    //Retorna o estado atual do pedido
    public OrderState getEstado() {
        return estado;
    }

    //Retorna o nome do estado atual
    public String getNomeEstado() {
        return estado.getNomeEstado();
    }

    //Retorna o ID do pedido
    public String getId() {
        return id;
    }

    //Retorna o nome do cliente
    public String getCliente() {
        return cliente;
    }

    //Retorna o valor do pedido
    public double getValor() {
        return valor;
    }

    //Retorna a data de criação do pedido
    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    //Verifica se o pedido pode ser cancelado
    public boolean podeSerCancelado() {
        return !(estado instanceof EntregueState);
    }

    //Verifica se o pedido está em um estado final
    public boolean isFinalizado() {
        return estado instanceof EntregueState || estado instanceof CanceladoState;
    }

    @Override
    public String toString() {
        return String.format("Pedido{id='%s', cliente='%s', valor=%.2f, estado='%s', dataCriacao=%s}",
                id, cliente, valor, getNomeEstado(), dataCriacao);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Order order = (Order) obj;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
