package com.gimnasio.gimnasioapi.controller;
import com.gimnasio.gimnasioapi.model.Material;
import com.gimnasio.gimnasioapi.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import  java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/materiales")
public class MaterialController {
    @Autowired
    private MaterialService materialService;
    @GetMapping
    public List<Material> listarTodos(){
        return materialService.listarTodos();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Material> buscarPorId(@PathVariable long id){
        Optional<Material> material =materialService.buscarPorId(id);
        return material.map(ResponseEntity::ok).
                orElseGet(() -> ResponseEntity.notFound().build());

    }
    @PostMapping
    public Material crear(@RequestBody Material material){
        return materialService.guardar(material);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Material> actualizar(@PathVariable long id , @RequestBody Material materialact){
        Optional<Material> material = materialService.buscarPorId(id);
        if(material.isPresent()){
            materialact.setId(id);
            return ResponseEntity.ok(materialService.guardar(materialact));}
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable long id){
        materialService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}