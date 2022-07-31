package com.hellfest22.springboot.repositories;

import com.hellfest22.springboot.model.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketsRepository extends JpaRepository<Tickets, String> {
    //los metodos findAll() y findById() vienen definidos en JpaRepository

    //Instruccion en SQL: SELECT * FROM tickets WHERE nombre = name AND apellido = fname
    @Query("select t from Tickets t where t.nombre = ?1 and t.apellido = ?2")
    Optional<Tickets> findByFullName(String name, String fname);
}
