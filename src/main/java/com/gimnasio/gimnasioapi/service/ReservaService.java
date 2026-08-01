package com.gimnasio.gimnasioapi.service;
import com.gimnasio.gimnasioapi.model.Reserva;
import com.gimnasio.gimnasioapi.model.Cliente;
import com.gimnasio.gimnasioapi.model.Actividad;
import com.gimnasio.gimnasioapi.repository.ActividadRepository;
import com.gimnasio.gimnasioapi.repository.ClienteRepository;
import com.gimnasio.gimnasioapi.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private ActividadRepository actividadRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    public Reserva crearReserva(Long actividadId, Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId).
                orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        Actividad actividad = actividadRepository.findById(actividadId).
                orElseThrow(() -> new RuntimeException("Actividad no encontrada"));
        long numeroDeReservas = reservaRepository.countByActividadIdAndEstado(actividadId, "CONFIRMADA");
        if (numeroDeReservas < actividad.getAforoMaximo()) {
            Reserva reserva = new Reserva();
            reserva.setCliente(cliente);
            reserva.setActividad(actividad);
            reserva.setEstado("CONFIRMADA");
            reserva.setFechaHora(LocalDateTime.now());
            return reservaRepository.save(reserva);
        }
        else{
            throw new RuntimeException("Aforo lleno");
        }

    }

    public void cancelar(Long reservaId) {
        Reserva reserva = reservaRepository.findById(reservaId)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        reserva.setEstado("CANCELADA");
        reservaRepository.save(reserva);
    }

    public List<Reserva> listarTodas() {
        return reservaRepository.findAll();
    }

    public Optional<Reserva> buscarPorId(Long id) {
        return reservaRepository.findById(id);
    }

}

