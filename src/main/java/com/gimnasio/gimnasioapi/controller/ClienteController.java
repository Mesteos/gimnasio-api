package com.gimnasio.gimnasioapi.controller;
import com.gimnasio.gimnasioapi.model.Cliente;
import com.gimnasio.gimnasioapi.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import  java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/clientes")
public class ClienteController {
@Autowired
private ClienteService clienteService;
@GetMapping
public List<Cliente> listarTodos(){
    return clienteService.listarTodos();
}
@GetMapping("/{id}")
public ResponseEntity<Cliente> buscarPorId(@PathVariable long id){
    Optional<Cliente> cliente =clienteService.buscarPorId(id);
    return cliente.map(ResponseEntity::ok).
            orElseGet(() -> ResponseEntity.notFound().build());

}
@PostMapping
public Cliente crear(@RequestBody Cliente cliente){
    return clienteService.guardar(cliente);
}
@PutMapping("/{id}")
public ResponseEntity<Cliente> actualizar(@PathVariable long id , @RequestBody Cliente clienteact){
    Optional<Cliente> cliente = clienteService.buscarPorId(id);
    if(cliente.isPresent()){
        clienteact.setId(id);
        return ResponseEntity.ok(clienteService.guardar(clienteact));}
    else{
        return ResponseEntity.notFound().build();
        }
    }

@DeleteMapping("/{id}")
public ResponseEntity<Void> eliminar(@PathVariable long id){
    clienteService.eliminar(id);
    return ResponseEntity.noContent().build();
}

}

