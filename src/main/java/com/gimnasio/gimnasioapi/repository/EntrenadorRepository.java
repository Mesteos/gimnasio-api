package com.gimnasio.gimnasioapi.repository;

import com.gimnasio.gimnasioapi.model.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrenadorRepository extends JpaRepository<Entrenador, Long> {
}