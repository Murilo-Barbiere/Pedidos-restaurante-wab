package com.murilo_dev.system_pedidos.service;

import com.murilo_dev.system_pedidos.model.CardapioModel;
import com.murilo_dev.system_pedidos.repository.CardapioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class CardapioServise {
    @Autowired
    private CardapioRepository cardapioRepository;

    public CardapioModel addCardapio(CardapioModel cardapio){
        return cardapioRepository.save(cardapio);
    }

    public List<CardapioModel> retornaCardapioCompleto(){
        return cardapioRepository.findAll();
    }

}
