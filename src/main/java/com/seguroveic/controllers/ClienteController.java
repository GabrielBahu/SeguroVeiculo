package com.seguroveic.controllers;

import com.seguroveic.dto.ErroDTO;
import com.seguroveic.entities.Cliente;
import com.seguroveic.repositories.ClienteRepository;
import com.seguroveic.service.ClienteService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ClienteController {

    private final ClienteRepository cltRepository;
    private final ClienteService cltService;

    public ClienteController(ClienteRepository cltRepository, ClienteService cltService) {
        this.cltRepository = cltRepository;
        this.cltService = cltService;
    }

    @PostMapping("/cliente")
    public ResponseEntity<?> criarCliente(@RequestBody Cliente cliente) {
        String mensagensErro = cltService.validarCliente(cliente);

        if (mensagensErro.isEmpty()) {
            Cliente c = cltRepository.save(cliente);
            return ResponseEntity.status(HttpStatus.OK).body(c);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroDTO(400, "Cliente", mensagensErro));
        }
    }

    @GetMapping("/cliente")
    public List<Cliente> buscarTodos(@RequestParam Optional<String> nome) {
        if (nome.isPresent()) {
            return cltRepository.findByNome(nome);
        } else {
            return cltRepository.findAll();
        }
    }

    @DeleteMapping("/cliente")
    public void deletar(@RequestParam String id) {
    	cltRepository.deleteById(id);
    }
}