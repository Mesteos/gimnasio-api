package com.gimnasio.gimnasioapi.service;
import com.gimnasio.gimnasioapi.model.Entrenador;
import com.gimnasio.gimnasioapi.repository.EntrenadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class EntrenadorService {
    @Autowired
    private EntrenadorRepository entrenadorRepository;

    public List<Entrenador> listarTodos(){
        return entrenadorRepository.findAll();
    }
    public Optional<Entrenador> buscarPorId(Long id){
        return entrenadorRepository.findById(id);
    }

    public Entrenador guardar(Entrenador entrenador){
        return entrenadorRepository.save(entrenador);
    }
    public void eliminar (Long id){
        entrenadorRepository.deleteById(id);
    }
}