package com.hellfest22.springboot.repositories;

import com.hellfest22.springboot.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Login, String> {
    //los metodos findAll() y findById() vienen definidos en JpaRepository

    //Instruccion en SQL: SELECT * FROM usuarios WHERE direccion_de_mail = mail AND password = pass
    @Query("select u from Login u where u.direccion_de_mail = ?1 and u.password = ?2 and u.rol = ?3")
    Optional<Login> findLoginBy(String mail, String password, String role);
}
