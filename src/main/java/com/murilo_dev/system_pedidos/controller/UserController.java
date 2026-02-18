package com.murilo_dev.system_pedidos.controller;

import com.murilo_dev.system_pedidos.DTO.LoginRequestDto;
import com.murilo_dev.system_pedidos.DTO.LoginResponseDto;
import com.murilo_dev.system_pedidos.model.UserModel;
import com.murilo_dev.system_pedidos.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/registrar")
    public ResponseEntity<?> registraUser(@Valid @RequestBody UserModel user){
        userService.registraUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> logar(@RequestBody LoginRequestDto userDados){
        Optional<LoginResponseDto> authResponse = userService.authenticaUser(userDados);

        if(authResponse.isPresent()){
            return ResponseEntity.ok(authResponse.get());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha invalidos");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/retorna_users")
    public List<UserModel> retornaUsers(){
        return userService.retornaUsers();
    }

    @GetMapping("/dados-usuario")
    public ResponseEntity<?> getDadosUser(Principal principal){
        Optional<UserModel> user = userService.userFindByName(principal.getName());
        if(user.isPresent()){
            return ResponseEntity.ok(new LoginResponseDto(
                    user.get().getId(),
                    user.get().getNome(),
                    user.get().getEmail(),
                    user.get().getRole()
            ));
        }
        return ResponseEntity.notFound().build();
    }

}