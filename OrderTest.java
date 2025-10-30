
// Testes unitários para validar as transições de estado do sistema de pedidos
// Demonstra o comportamento correto do padrão State implementado

public class OrderTest {

    public static void main(String[] args) {
        System.out.println("Testes Unitários\n");

        int testesExecutados = 0;
        int testesPassaram = 0;

        // Teste 1: Criação de pedido
        testesExecutados++;
        if (testarCriacaoPedido()) {
            testesPassaram++;
            System.out.println("Teste 1: Criação de pedido - PASSOU");
        } else {
            System.out.println("Teste 1: Criação de pedido - FALHOU");
        }

        // Teste 2: Fluxo completo de pedido
        testesExecutados++;
        if (testarFluxoCompleto()) {
            testesPassaram++;
            System.out.println("Teste 2: Fluxo completo - PASSOU");
        } else {
            System.out.println("Teste 2: Fluxo completo - FALHOU");
        }

        // Teste 3: Cancelamento de pedido novo
        testesExecutados++;
        if (testarCancelamentoPedidoNovo()) {
            testesPassaram++;
            System.out.println("Teste 3: Cancelamento pedido novo - PASSOU");
        } else {
            System.out.println("Teste 3: Cancelamento pedido novo - FALHOU");
        }

        // Teste 4: Ações inválidas
        testesExecutados++;
        if (testarAcoesInvalidas()) {
            testesPassaram++;
            System.out.println("Teste 4: Ações inválidas - PASSOU");
        } else {
            System.out.println("Teste 4: Ações inválidas - FALHOU");
        }

        // Teste 5: Estados finais
        testesExecutados++;
        if (testarEstadosFinais()) {
            testesPassaram++;
            System.out.println("Teste 5: Estados finais - PASSOU");
        } else {
            System.out.println("Teste 5: Estados finais - FALHOU");
        }

        // Resumo dos testes
        System.out.println("\nResumo dos testes:");
        System.out.println("Testes executados: " + testesExecutados);
        System.out.println("Testes que passarma: " + testesPassaram);
        System.out.println("Testes que falharam: " + (testesExecutados - testesPassaram));
        System.out.println("Taxa de sucesso: " + (testesPassaram * 100 / testesExecutados) + "%");
    }


    //Testa a criação de um pedido no estado inicial.
    private static boolean testarCriacaoPedido() {
        try {
            Order pedido = new Order("TESTE-1", "Cliente Teste", 100.0);

            // Verifica se o pedido foi criado no estado correto
            if (!"Novo".equals(pedido.getNomeEstado())) {
                System.out.println("  Erro: Estado inicial deveria ser 'Novo', mas é '" + pedido.getNomeEstado() + "'");
                return false;
            }

            // Verifica se o pedido não está finalizado
            if (pedido.isFinalizado()) {
                System.out.println("  Erro: Pedido novo não deveria estar finalizado");
                return false;
            }

            // Verifica se pode ser cancelado
            if (!pedido.podeSerCancelado()) {
                System.out.println("  Erro: Pedido novo deveria poder ser cancelado");
                return false;
            }

            return true;
        } catch (Exception e) {
            System.out.println("  Erro inesperado: " + e.getMessage());
            return false;
        }
    }

    //Testa o fluxo completo: Novo -> Pago -> Enviado -> Entregue
    private static boolean testarFluxoCompleto() {
        try {
            Order pedido = new Order("TESTE-2", "Cliente Teste", 200.0);

            // Estado inicial
            if (!"Novo".equals(pedido.getNomeEstado())) {
                System.out.println("  Erro: Estado inicial deveria ser 'Novo'");
                return false;
            }

            // Pagamento
            pedido.pagar();
            if (!"Pago".equals(pedido.getNomeEstado())) {
                System.out.println("  Erro: Estado após pagamento deveria ser 'Pago'");
                return false;
            }

            // Envio
            pedido.enviar();
            if (!"Enviado".equals(pedido.getNomeEstado())) {
                System.out.println("  Erro: Estado após envio deveria ser 'Enviado'");
                return false;
            }

            // Entrega
            pedido.entregar();
            if (!"Entregue".equals(pedido.getNomeEstado())) {
                System.out.println("  Erro: Estado após entrega deveria ser 'Entregue'");
                return false;
            }

            // Verifica se está finalizado
            if (!pedido.isFinalizado()) {
                System.out.println("  Erro: Pedido entregue deveria estar finalizado");
                return false;
            }

            return true;
        } catch (Exception e) {
            System.out.println("  Erro inesperado: " + e.getMessage());
            return false;
        }
    }

    //Testa o cancelamento de um pedido no estado novo.
    private static boolean testarCancelamentoPedidoNovo() {
        try {
            Order pedido = new Order("TESTE-3", "Cliente Teste", 150.0);

            // Cancela o pedido
            pedido.cancelar();

            // Verifica se mudou para cancelado
            if (!"Cancelado".equals(pedido.getNomeEstado())) {
                System.out.println("  Erro: Estado após cancelamento deveria ser 'Cancelado'");
                return false;
            }

            // Verifica se está finalizado
            if (!pedido.isFinalizado()) {
                System.out.println("  Erro: Pedido cancelado deveria estar finalizado");
                return false;
            }

            return true;
        } catch (Exception e) {
            System.out.println("  Erro inesperado: " + e.getMessage());
            return false;
        }
    }

    //Testa ações inválidas em diferentes estados.
    private static boolean testarAcoesInvalidas() {
        try {
            Order pedido = new Order("TESTE-4", "Cliente Teste", 300.0);

            // Tenta enviar sem pagar
            try {
                pedido.enviar();
                System.out.println("  Erro: Deveria ter lançado exceção ao tentar enviar pedido não pago");
                return false;
            } catch (IllegalStateException e) {
                // Esperado
            }

            // Paga o pedido
            pedido.pagar();

            // Tenta entregar sem enviar
            try {
                pedido.entregar();
                System.out.println("  Erro: Deveria ter lançado exceção ao tentar entregar pedido não enviado");
                return false;
            } catch (IllegalStateException e) {
                // Esperado
            }

            // Envia o pedido
            pedido.enviar();

            // Entrega o pedido
            pedido.entregar();

            // Tenta cancelar pedido entregue
            try {
                pedido.cancelar();
                System.out.println("  Erro: Deveria ter lançado exceção ao tentar cancelar pedido entregue");
                return false;
            } catch (IllegalStateException e) {
                // Esperado
            }

            return true;
        } catch (Exception e) {
            System.out.println("  Erro inesperado: " + e.getMessage());
            return false;
        }
    }

    //Testa comportamentos de estados finais.
    private static boolean testarEstadosFinais() {
        try {
            // Testa pedido entregue
            Order pedidoEntregue = new Order("TESTE-5", "Cliente Teste", 400.0);
            pedidoEntregue.pagar();
            pedidoEntregue.enviar();
            pedidoEntregue.entregar();

            if (!pedidoEntregue.isFinalizado()) {
                System.out.println("  Erro: Pedido entregue deveria estar finalizado");
                return false;
            }

            if (pedidoEntregue.podeSerCancelado()) {
                System.out.println("  Erro: Pedido entregue não deveria poder ser cancelado");
                return false;
            }

            // Testa pedido cancelado
            Order pedidoCancelado = new Order("TESTE-6", "Cliente Teste", 500.0);
            pedidoCancelado.cancelar();

            if (!pedidoCancelado.isFinalizado()) {
                System.out.println("  Erro: Pedido cancelado deveria estar finalizado");
                return false;
            }

            if (pedidoCancelado.podeSerCancelado()) {
                System.out.println("  Erro: Pedido cancelado não deveria poder ser cancelado");
                return false;
            }

            return true;
        } catch (Exception e) {
            System.out.println("  Erro inesperado: " + e.getMessage());
            return false;
        }
    }
}
