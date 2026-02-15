package com.murilo_dev.system_pedidos.DTO;

public record LoginResponseDto(Long id, String nome, String email, RoleDto role) {
}