package com.gimnasio.gimnasioapi.repository;

import com.gimnasio.gimnasioapi.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}