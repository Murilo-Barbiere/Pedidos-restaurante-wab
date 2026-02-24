package com.murilo_dev.system_pedidos.controller;

import com.murilo_dev.system_pedidos.DTO.DadosHistoricoDto;
import com.murilo_dev.system_pedidos.DTO.DadosPedidosDto;
import com.murilo_dev.system_pedidos.model.PedidosModel;
import com.murilo_dev.system_pedidos.service.PedidosServise;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidosController {

    private PedidosServise pedidosServise;

    public PedidosController(PedidosServise pedidosServise) {
        this.pedidosServise = pedidosServise;
    }

    //metodos:
    @PostMapping("/fazer_pedido")
    public void fazerPedido(@RequestBody PedidosModel pedido){
        pedidosServise.savePedido(pedido);
    }

    @GetMapping("/mostrar_pedidos")
    public List<DadosPedidosDto> mostrarPedidos(){
        return pedidosServise.retornPedidos();
    }

    @GetMapping("/mostra_historico")
    public List<DadosHistoricoDto> mostrarHistorico(){
        return pedidosServise.retornHistorico();
    }

    @DeleteMapping("/pediu/{id}")
    public void pedir(@PathVariable("id") Integer id_pedido){
        pedidosServise.pedidoRealizado(id_pedido);
    }

    //deletar pedido

    //mostrar pedidos do um X

    //no func que deleta pedidos que
}