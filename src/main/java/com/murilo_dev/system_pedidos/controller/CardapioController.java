package com.murilo_dev.system_pedidos.controller;

import com.murilo_dev.system_pedidos.model.CardapioModel;
import com.murilo_dev.system_pedidos.service.CardapioServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cardapio")
public class CardapioController {
    @Autowired
    private CardapioServise cardapioServise;

    //deletar item do cardapio

    @GetMapping("/exibir_cardapio")
    public List<CardapioModel> exibirCardapio(){
        return cardapioServise.retornaCardapioCompleto();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add_elemento_cardapio")
    public void addElementoCardapio(@RequestBody CardapioModel elemento){
        cardapioServise.addCardapio(elemento);
    }
}