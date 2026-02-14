package com.murilo_dev.system_pedidos.controller;

import com.murilo_dev.system_pedidos.DTO.LoginRequestDto;
import com.murilo_dev.system_pedidos.model.UserModel;
import com.murilo_dev.system_pedidos.service.UserService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/registrar")
    @ResponseStatus(HttpStatus.CREATED)
    public void registraUser(@Valid @RequestBody UserModel user){
        userService.registraUser(user);
    }

    @PostMapping("/login")
    public boolean logar(@RequestBody LoginRequestDto userDados){
        return userService.isRegistered(userDados.nome(), userDados.senha());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/retorna_users")
    public List<UserModel> retornaUsers(){
        return userService.retornaUsers();
    }
}