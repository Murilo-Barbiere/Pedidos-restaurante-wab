package com.murilo_dev.system_pedidos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UsuarioDetailsService usuarioDetailsService;

    public SecurityConfig(UsuarioDetailsService usuarioDetailsService) {
        this.usuarioDetailsService = usuarioDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Páginas públicas - LIBERAR TUDO QUE É ESTÁTICO
                        .requestMatchers("/", "/index.html", "/login.html", "/registro.html").permitAll()
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers("/js/**").permitAll()
                        .requestMatchers("/images/**").permitAll()

                        // Rotas da API públicas
                        .requestMatchers("/user/registrar").permitAll()
                        .requestMatchers("/user/login").permitAll()  // URL de processamento do login

                        // Rotas protegidas
                        .requestMatchers("/cardapio/add_elemento_cardapio").hasRole("ADMIN")
                        .requestMatchers("/user/retorna_users").hasRole("ADMIN")
                        .requestMatchers("/cardapio/exibir_cardapio").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/pedidos/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/cardapio.html").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/admPagina.html").hasRole("ADMIN")

                        // Qualquer outra requisição precisa de autenticação
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login.html")
                        .loginProcessingUrl("/processar-login")
                        .usernameParameter("nome")
                        .passwordParameter("senha")
                        .defaultSuccessUrl("/cardapio.html", true)
                        .failureUrl("/login.html?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login.html?logout=true")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                .userDetailsService(usuarioDetailsService);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}