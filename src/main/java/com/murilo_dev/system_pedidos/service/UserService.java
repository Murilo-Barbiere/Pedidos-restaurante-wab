package com.murilo_dev.system_pedidos.service;

import com.murilo_dev.system_pedidos.DTO.LoginRequestDto;
import com.murilo_dev.system_pedidos.DTO.LoginResponseDto;
import com.murilo_dev.system_pedidos.model.UserModel;
import com.murilo_dev.system_pedidos.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<LoginResponseDto> authenticaUser(LoginRequestDto loginRequest){
        Optional<UserModel> user = userRepository.findByNome(loginRequest.nome());
        if(user.isPresent()){
            boolean senhaValida = passwordEncoder.matches(loginRequest.senha(), user.get().getSenha());
            if(senhaValida){
                LoginResponseDto loginResponse = new LoginResponseDto(
                        user.get().getId(),
                        user.get().getNome(),
                        user.get().getEmail(),
                        user.get().getRole()
                );
                return Optional.of(loginResponse);
            }
        }
        return Optional.empty();
    }

    public List<UserModel> retornaUsers(){
        return userRepository.findAll();
    }

    public Optional<UserModel> userFindByName(String nome){
        return userRepository.findByNome(nome);
    }
}