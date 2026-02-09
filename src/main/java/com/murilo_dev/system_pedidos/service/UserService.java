package com.murilo_dev.system_pedidos.service;

import com.murilo_dev.system_pedidos.model.UserModel;
import com.murilo_dev.system_pedidos.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserModel registraUser(UserModel dadosDoUsuario){
        String senhaCod = passwordEncoder.encode(dadosDoUsuario.getSenha());
        dadosDoUsuario.setSenha(senhaCod);

        return userRepository.save(dadosDoUsuario);
    }

    public List<UserModel> retornaUsers(){
        return userRepository.findAll();
    }
}