//Sistema de gestão de pedidos online
//Demonstra o uso do padrão State para gerenciar o ciclo de vida dos pedidos

public class OrderManagementSystem {

    public static void main(String[] args) {
        System.out.println("Sistema de Gestão de Pedidos Online\n");

        // Exemplo 1: Fluxo completo de um pedido
        demonstrarFluxoCompleto();

        System.out.println("\n" + "=".repeat(50) + "\n");

        // Exemplo 2: Tentativas de ações inválidas
        demonstrarAcoesInvalidas();

        System.out.println("\n" + "=".repeat(50) + "\n");

        // Exemplo 3: Cancelamento de pedido
        demonstrarCancelamento();
    }

    //Demonstra o fluxo completo de um pedido: Novo -> Pago -> Enviado -> Entregue
    private static void demonstrarFluxoCompleto() {
        System.out.println("Fluxo Completo de um Pedido:");

        Order pedido = new Order("PEDIDO-1", "Cliente 1", 299.99);
        System.out.println("Pedido criado: " + pedido);

        // Pagamento
        pedido.pagar();
        System.out.println("Estado após pagamento: " + pedido.getNomeEstado());

        // Envio
        pedido.enviar();
        System.out.println("Estado após envio: " + pedido.getNomeEstado());

        // Entrega
        pedido.entregar();
        System.out.println("Estado após entrega: " + pedido.getNomeEstado());
        System.out.println("Pedido finalizado: " + pedido.isFinalizado());
    }

    //Demonstra tentativas de ações inválidas em diferentes estados
    private static void demonstrarAcoesInvalidas() {
        System.out.println("Tentativas de Ações Inválidas:");

        Order pedido = new Order("PEDIDO-2", "Cliente 2", 150.00);
        System.out.println("Pedido criado: " + pedido);

        // Tentativa de envio sem pagamento
        try {
            pedido.enviar();
        } catch (IllegalStateException e) {
            System.out.println("Erro esperado: " + e.getMessage());
        }

        // Pagamento válido
        pedido.pagar();
        System.out.println("Estado após pagamento: " + pedido.getNomeEstado());

        // Tentativa de entrega sem envio
        try {
            pedido.entregar();
        } catch (IllegalStateException e) {
            System.out.println("Erro esperado: " + e.getMessage());
        }

        // Envio válido
        pedido.enviar();
        System.out.println("Estado após envio: " + pedido.getNomeEstado());

        // Entrega válida
        pedido.entregar();
        System.out.println("Estado após entrega: " + pedido.getNomeEstado());

        // Tentativa de ação após entrega
        try {
            pedido.cancelar();
        } catch (IllegalStateException e) {
            System.out.println("Erro esperado: " + e.getMessage());
        }
    }

    //Demonstra o cancelamento de pedidos em diferentes estados
    private static void demonstrarCancelamento() {
        System.out.println("Cancelamento de Pedidos:");

        // Cancelamento de pedido novo
        Order pedidoNovo = new Order("PEDIDO-3", "Cliente 3", 75.50);
        System.out.println("Pedido novo: " + pedidoNovo);
        pedidoNovo.cancelar();
        System.out.println("Estado após cancelamento: " + pedidoNovo.getNomeEstado());

        // Cancelamento de pedido pago
        Order pedidoPago = new Order("PEDIDO-4", "Cliente 4", 200.00);
        pedidoPago.pagar();
        System.out.println("Pedido pago: " + pedidoPago);
        pedidoPago.cancelar();
        System.out.println("Estado após cancelamento: " + pedidoPago.getNomeEstado());

        // Cancelamento de pedido enviado
        Order pedidoEnviado = new Order("PEDIDO-5", "Cliente 5", 350.00);
        pedidoEnviado.pagar();
        pedidoEnviado.enviar();
        System.out.println("Pedido enviado: " + pedidoEnviado);
        pedidoEnviado.cancelar();
        System.out.println("Estado após cancelamento: " + pedidoEnviado.getNomeEstado());
    }
}
