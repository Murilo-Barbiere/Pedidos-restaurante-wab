package com.murilo_dev.system_pedidos.repository;

import com.murilo_dev.system_pedidos.model.CardapioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardapioRepository extends JpaRepository<CardapioModel, Long> {
}
