package com.murilo_dev.system_pedidos.controller;

import com.murilo_dev.system_pedidos.model.PedidosModel;
import com.murilo_dev.system_pedidos.service.PedidosServise;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class pedidosController {

    private PedidosServise pedidosServise;

    public pedidosController(PedidosServise pedidosServise) {
        this.pedidosServise = pedidosServise;
    }

    //metodos:
    @PostMapping("/fazer_pedido")
    public void fazerPedido(@RequestBody PedidosModel pedido){
        pedidosServise.savePedido(pedido);
    }

    @GetMapping("/mostrar_pedidos")
    public List<PedidosModel> mostrarPedidos(){
        return pedidosServise.retornPedidos();
    }

    //fazer pedido

    //deletar pedido

    //mostrar pedidos do um X

}
