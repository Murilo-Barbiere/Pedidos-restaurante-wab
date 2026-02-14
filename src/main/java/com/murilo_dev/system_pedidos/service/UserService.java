package com.murilo_dev.system_pedidos.service;

import com.murilo_dev.system_pedidos.model.UserModel;
import com.murilo_dev.system_pedidos.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

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

    public boolean isRegistered(String userName, String userSenha){
        Optional<UserModel> user = userRepository.findByNome(userName);

        if(user.isPresent()){
            return passwordEncoder.matches(userSenha, user.get().getSenha());
        }
        return false;
    }

    public List<UserModel> retornaUsers(){
        return userRepository.findAll();
    }
}