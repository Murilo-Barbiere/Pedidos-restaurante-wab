package com.murilo_dev.system_pedidos.DTO;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

public record DadosPedidosDto(Integer id,
                              String nome_user,
                              String item_cardapio,
                              BigDecimal valor,
                              LocalDateTime data_hora_pedido) {
}