package com.gimnasio.gimnasioapi.service;
import com.gimnasio.gimnasioapi.model.Cliente;
import com.gimnasio.gimnasioapi.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ClienteService {
@Autowired
private ClienteRepository clienteRepository;

public List<Cliente> listarTodos(){
   return clienteRepository.findAll();
}
public Optional<Cliente> buscarPorId(Long id){
    return clienteRepository.findById(id);
}

public Cliente guardar(Cliente cliente){
    return clienteRepository.save(cliente);
}
public void eliminar (Long id){
    clienteRepository.deleteById(id);
}
}
