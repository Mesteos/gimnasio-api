package com.gimnasio.gimnasioapi.controller;
import com.gimnasio.gimnasioapi.model.Entrenador;
import com.gimnasio.gimnasioapi.service.EntrenadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import  java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/entrenadores")
public class EntrenadorController {
    @Autowired
    private EntrenadorService entrenadorService;
    @GetMapping
    public List<Entrenador> listarTodos(){
        return entrenadorService.listarTodos();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Entrenador> buscarPorId(@PathVariable long id){
        Optional<Entrenador> entrenador =entrenadorService.buscarPorId(id);
        return entrenador.map(ResponseEntity::ok).
                orElseGet(() -> ResponseEntity.notFound().build());

    }
    @PostMapping
    public Entrenador crear(@RequestBody Entrenador entrenador){
        return entrenadorService.guardar(entrenador);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Entrenador> actualizar(@PathVariable long id , @RequestBody Entrenador entrenadoract){
        Optional<Entrenador> entrenador = entrenadorService.buscarPorId(id);
        if(entrenador.isPresent()){
            entrenadoract.setId(id);
            return ResponseEntity.ok(entrenadorService.guardar(entrenadoract));}
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable long id){
        entrenadorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
