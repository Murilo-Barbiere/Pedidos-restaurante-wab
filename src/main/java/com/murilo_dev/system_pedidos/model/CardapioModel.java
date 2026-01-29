package com.murilo_dev.system_pedidos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cardapio")
public class CardapioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cardapio_id")
    private Long id;
    @NotNull
    @Column(name = "nome")
    String nome;
    @NotNull
    @Column(name = "valor")
    double valor;
    @Column(name = "descricao")
    String descricao;
}