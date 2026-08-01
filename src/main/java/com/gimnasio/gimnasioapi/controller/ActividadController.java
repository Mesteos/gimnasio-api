package com.gimnasio.gimnasioapi.controller;
import com.gimnasio.gimnasioapi.model.Actividad;
import com.gimnasio.gimnasioapi.service.ActividadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import  java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/actividades")
public class ActividadController {
@Autowired
private ActividadService actividadService;
@GetMapping
public List<Actividad> listarTodos(){
    return actividadService.listarTodos();
}
@GetMapping("/{id}")
public ResponseEntity<Actividad> buscarPorId(@PathVariable long id){
    Optional<Actividad> actividad= actividadService.buscarPorId(id);
    return actividad.map(ResponseEntity::ok).
            orElseGet(()->ResponseEntity.notFound().build());
}
@PostMapping
public Actividad crear(@RequestBody Actividad actividad){
    return actividadService.guardar(actividad);
}
@PutMapping("/{id}")
public ResponseEntity<Actividad> actualizar(@PathVariable long id,@RequestBody Actividad actividadact) {
    Optional<Actividad> actividad= actividadService.buscarPorId(id);
    if(actividad.isPresent()) {
        actividadact.setId(id);
        return ResponseEntity.ok(actividadService.guardar(actividadact));
    }
    else {
        return ResponseEntity.notFound().build();
    }
}
@DeleteMapping("/{id}")
public ResponseEntity<Void> eliminar(@PathVariable long id){
    actividadService.eliminar(id);
    return ResponseEntity.noContent().build();
}

}

