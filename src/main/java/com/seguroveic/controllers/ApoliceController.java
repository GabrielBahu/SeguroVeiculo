package com.seguroveic.controllers;

import com.seguroveic.dto.ApoliceStatusDTO;
import com.seguroveic.dto.ErroDTO;
import com.seguroveic.entities.Apolice;
import com.seguroveic.repositories.ApoliceRepository;
import com.seguroveic.service.ApoliceService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ApoliceController {

    private final ApoliceRepository aporepository;
    private final ApoliceService apoService;

    public ApoliceController(ApoliceRepository aporepository, ApoliceService apoService) {
        this.aporepository = aporepository;
        this.apoService = apoService;
    }

    @PostMapping("/apolice")
    public ResponseEntity<?> criarApolice(@RequestBody Apolice apolice) {
        if (apolice.getId() == null) {
            apolice.setNumero(apoService.gerarNumeroUnico());
        }

        String mensagensErro = apoService.validarApolice(apolice);

        if (mensagensErro.isEmpty()) {
            Apolice c = aporepository.save(apolice);
            return ResponseEntity.status(HttpStatus.OK).body(c);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroDTO(400, "Apolice", mensagensErro));
        }
    }

    @GetMapping("/apolice")
    public List<Apolice> buscarTodas(@RequestParam Optional<Integer> numero) {
        if (numero.isPresent()) {
            return aporepository.findByNumero(numero);
        } else {
            return aporepository.findAll();
        }
    }

    @GetMapping("/apolice/consultar")
    public List<ApoliceStatusDTO> consultarApolice(@RequestParam Integer numero) {
        return apoService.consultarApolices(numero);
    }

    @DeleteMapping("/apolice")
    public void deletar(@RequestParam String id) {
    	aporepository.deleteById(id);
    }
}
