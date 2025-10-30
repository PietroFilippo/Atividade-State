# Sistema de Gestão de Pedidos Online com padrão State

Este projeto implementa um sistema de gestão de pedidos online utilizando o Padrão State. O sistema gerencia o ciclo de vida completo de um pedido, desde sua criação até a entrega ou cancelamento.

O sistema permite gerenciar pedidos que podem estar em um dos seguintes estados:

- Novo - Pedido criado, aguardando pagamento
- Pago - Pagamento confirmado, aguardando envio
- Enviado - Pedido despachado para o cliente
- Entregue - Pedido recebido pelo cliente
- Cancelado - Pedido cancelado

Cada estado define quais ações são permitidas:

| Estado Atual | Ações Permitidas | Próximo Estado |
|--------------|------------------|----------------|
| Novo | `pagar()`, `cancelar()` | Pago, Cancelado |
| Pago | `enviar()`, `cancelar()` | Enviado, Cancelado |
| Enviado | `entregar()`, `cancelar()` | Entregue, Cancelado |
| Entregue | - | (Estado final) |
| Cancelado | - | (Estado final) |



