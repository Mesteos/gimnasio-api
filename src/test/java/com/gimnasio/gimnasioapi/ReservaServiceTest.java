package com.gimnasio.gimnasioapi;

import com.gimnasio.gimnasioapi.model.Actividad;
import com.gimnasio.gimnasioapi.model.Cliente;
import com.gimnasio.gimnasioapi.model.Reserva;
import com.gimnasio.gimnasioapi.repository.ActividadRepository;
import com.gimnasio.gimnasioapi.repository.ClienteRepository;
import com.gimnasio.gimnasioapi.repository.ReservaRepository;
import com.gimnasio.gimnasioapi.service.ReservaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReservaServiceTest {

    @Mock
    private ReservaRepository reservaRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ActividadRepository actividadRepository;

    @InjectMocks
    private ReservaService reservaService;

    @Test
    void noDejaCrearReservaSiAforoEstaLleno() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);

        Actividad actividad = new Actividad();
        actividad.setId(1L);
        actividad.setAforoMaximo(2);

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(actividadRepository.findById(1L)).thenReturn(Optional.of(actividad));
        when(reservaRepository.countByActividadIdAndEstado(1L, "CONFIRMADA")).thenReturn(2L);

        assertThrows(RuntimeException.class, () -> {
            reservaService.crearReserva(1L, 1L);
        });
    }
    @Test
    void permiteCrearReservaSiHayHueco() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);

        Actividad actividad = new Actividad();
        actividad.setId(1L);
        actividad.setAforoMaximo(2);

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(actividadRepository.findById(1L)).thenReturn(Optional.of(actividad));
        when(reservaRepository.countByActividadIdAndEstado(1L, "CONFIRMADA")).thenReturn(1L);
        when(reservaRepository.save(org.mockito.ArgumentMatchers.any(Reserva.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Reserva resultado = reservaService.crearReserva(1L, 1L);

        assertNotNull(resultado);
        assertEquals("CONFIRMADA", resultado.getEstado());
    }
    @Test
    void  cancelarReservaCambiaEstadoACancelada(){
        Reserva reserva = new Reserva();
        reserva.setId(1L);
        reserva.setEstado("CONFIRMADA");

        when(reservaRepository.findById(1L)).thenReturn(Optional.of(reserva));
        when(reservaRepository.save(org.mockito.ArgumentMatchers.any(Reserva.class))).
                thenAnswer(invocacion->invocacion.getArgument(0));

        reservaService.cancelar(1L);
        assertEquals("CANCELADA",reserva.getEstado());
    }
}
