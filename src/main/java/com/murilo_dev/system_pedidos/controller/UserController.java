package com.murilo_dev.system_pedidos.controller;

import com.murilo_dev.system_pedidos.model.UserModel;
import com.murilo_dev.system_pedidos.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //metodos user
    @GetMapping("/retorna_users")
    public Optional<List<UserModel>> retornasUserByID(){
        return Optional.ofNullable(userService.retornaUsers());
    }

    @PostMapping("/registra_users")
    public void registraUser(@RequestBody UserModel user){
        userService.registraUser(user);
    }
}