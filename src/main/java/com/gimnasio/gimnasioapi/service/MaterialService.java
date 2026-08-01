
package com.gimnasio.gimnasioapi.service;
import com.gimnasio.gimnasioapi.model.Material ;
import com.gimnasio.gimnasioapi.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MaterialService {
    @Autowired
    private MaterialRepository  materialRepository;


    public List<Material> listarTodos(){
        return  materialRepository.findAll();
    }
    public Optional<Material> buscarPorId(Long id){
        return materialRepository.findById(id);
    }

    public  Material   guardar( Material material){
        return materialRepository.save(material);
    }
    public void eliminar (Long id){
        materialRepository.deleteById(id);
    }
}