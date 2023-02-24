package com.seguroveic.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import com.seguroveic.entities.Cliente;

public interface ClienteRepository extends MongoRepository<Cliente, String> {

    List<Cliente> findByNome(@Param("nome") Optional<String> nome);
    List<Cliente> findByCpf(@Param("cpf") String cpf);
}
