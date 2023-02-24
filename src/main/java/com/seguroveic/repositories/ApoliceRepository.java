package com.seguroveic.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import com.seguroveic.entities.Apolice;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ApoliceRepository extends MongoRepository<Apolice, String> {

    List<Apolice> findByNumero(@Param("numero") Optional<Integer> numero);
    List<Apolice> findByNumero(@Param("numero") Integer numero);
}