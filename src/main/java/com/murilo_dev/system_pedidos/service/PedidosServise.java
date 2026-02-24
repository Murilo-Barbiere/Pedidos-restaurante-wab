package com.murilo_dev.system_pedidos.service;

import com.murilo_dev.system_pedidos.DTO.DadosHistoricoDto;
import com.murilo_dev.system_pedidos.DTO.DadosPedidosDto;
import com.murilo_dev.system_pedidos.model.PedidosModel;
import com.murilo_dev.system_pedidos.repository.PedidosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidosServise {

    private PedidosRepository pedidosRepository;

    public PedidosServise(PedidosRepository pedidosRepository) {
        this.pedidosRepository = pedidosRepository;
    }

    public void savePedido(PedidosModel pedidosModel){
        pedidosRepository.save(pedidosModel);
    }

    public void pedidoRealizado(Integer id_pedido){
        pedidosRepository.pedido_realizado(id_pedido);
    }

    public List<DadosPedidosDto> retornPedidos(){
        return pedidosRepository.dadosPedidos();
    }

    public List<DadosHistoricoDto> retornHistorico(){
        return pedidosRepository.dadosHistorico();
    }
}