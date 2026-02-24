package com.murilo_dev.system_pedidos.repository;

import com.murilo_dev.system_pedidos.DTO.DadosHistoricoDto;
import com.murilo_dev.system_pedidos.DTO.DadosPedidosDto;
import com.murilo_dev.system_pedidos.model.PedidosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PedidosRepository extends JpaRepository<PedidosModel,Long> {

    @Procedure(procedureName = "pedido_realizado")
    void pedido_realizado(@Param("id_pedido_feito") Integer id_pedido);

    @Query(value = "SELECT * from dados_pedidos", nativeQuery = true)
    List<DadosPedidosDto> dadosPedidos();

    @Query(value = "SELECT * from dados_historico", nativeQuery = true)
    List<DadosHistoricoDto> dadosHistorico();
}