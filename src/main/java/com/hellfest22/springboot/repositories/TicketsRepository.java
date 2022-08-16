package com.hellfest22.springboot.repositories;

import com.hellfest22.springboot.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketsRepository extends JpaRepository<Ticket, String> {
    //los metodos findAll(), findById() y save() vienen definidos en JpaRepository

    //Instruccion en SQL: SELECT * FROM tickets WHERE nombre = name AND apellido = fname
    @Query("select t from Ticket t where t.nombre = ?1 and t.apellido = ?2")
    List<Ticket> findByFullName(String name, String fname);

    //Instruccion en SQL: SELECT * FROM tickets WHERE direccion_de_mail = mail
    @Query("select t from Ticket t where t.direccionDeMail = ?1")
    List<Ticket> findByEmail(String email);
}
