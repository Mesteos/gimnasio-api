package com.gimnasio.gimnasioapi.service;
import com.gimnasio.gimnasioapi.model.Actividad ;
import com.gimnasio.gimnasioapi.repository.ActividadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ActividadService {
    @Autowired
    private ActividadRepository  actividadRepository;


    public List<Actividad> listarTodos(){
        return  actividadRepository.findAll();
    }
    public Optional<Actividad> buscarPorId(Long id){
        return actividadRepository.findById(id);
    }

    public  Actividad   guardar( Actividad actividad){
        return actividadRepository.save(actividad);
    }
    public void eliminar (Long id){
        actividadRepository.deleteById(id);
    }
}
