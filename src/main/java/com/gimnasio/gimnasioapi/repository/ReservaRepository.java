package com.gimnasio.gimnasioapi.repository;

import com.gimnasio.gimnasioapi.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    long countByActividadIdAndEstado(Long actividadId, String estado);
}