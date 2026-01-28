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
    @NotNull
    @Column(name = "user_id")
    private Long ID;
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

    public UserModel(Long ID, String nome, String senha, String email) {
        this.ID = ID;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
    }

    public UserModel() {
    }
}