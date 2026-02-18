package com.murilo_dev.system_pedidos.security;

import com.murilo_dev.system_pedidos.model.UserModel;
import com.murilo_dev.system_pedidos.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public UsuarioDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {
       UserModel usermodel = userRepository.findByNome(nome)
               .orElseThrow(()-> new UsernameNotFoundException("user nao existe"));
       return new UsuarioDetails(usermodel);
    }
}