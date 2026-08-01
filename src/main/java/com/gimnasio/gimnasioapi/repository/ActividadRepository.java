package com.gimnasio.gimnasioapi.repository;

import com.gimnasio.gimnasioapi.model.Actividad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActividadRepository extends JpaRepository<Actividad, Long> {
}