package com.murilo_dev.system_pedidos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "usuario")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @NotNull
    @Column(name = "nome")
    private String nome;
    @NotNull
    @Column(name = "senha")
    private String senha;
    @NotNull
    @Email
    @Column(name = "email")
    private String email;
}