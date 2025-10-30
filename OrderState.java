//Interface que define o contrato para todos os estados de um pedido
//Implementa o padrão State para gerenciar transições de estado de forma encapsulada

public interface OrderState {

    //Processa o pagamento do pedido
    void pagar(Order order);

    //Envia o pedido para o cliente
    void enviar(Order order);

    //Confirma a entrega do pedido ao cliente
    void entregar(Order order);

    //Cancela o pedido
    void cancelar(Order order);

    //Retorna o nome do estado atual
    String getNomeEstado();
}
