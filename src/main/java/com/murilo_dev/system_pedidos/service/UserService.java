package com.murilo_dev.system_pedidos.service;

import com.murilo_dev.system_pedidos.controller.UserController;
import com.murilo_dev.system_pedidos.model.UserModel;
import com.murilo_dev.system_pedidos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserModel registraUser(UserModel dadosDoUsuario){
        return userRepository.save(dadosDoUsuario);
    }

    public List<UserModel> retornaUsers(){
        return userRepository.findAll();
    }
}