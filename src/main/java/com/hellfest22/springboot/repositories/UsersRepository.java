package com.hellfest22.springboot.repositories;

import com.hellfest22.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, String> {
    //los metodos findAll(), findById() y save() vienen definidos en JpaRepository

    //Instruccion en SQL: SELECT * FROM Usuarios WHERE direccion_de_mail = mail
    @Query("select u from User u where u.direccion_de_mail = ?1")
    List<User> findByEmail(String email);

    //Consulta para obtener el usuario segun Id de tipo BigInteger
    @Query("select u from User u where u.id_user = ?1")
    Optional<User> findById(BigInteger id);

//    //Modificar un User por su Id de tipo BigInteger:
//    @Modifying(flushAutomatically = true, clearAutomatically = true)
//    @Query("update User u set u.nombre = 'XXX' where u.id_user =: newU")
//    void updateById(@Param("newU") User newU);
//
//    //Eliminar un User por su Id de tipo BigInteger:
//    @Modifying(flushAutomatically = true, clearAutomatically = true)
//    @Query("delete from User u where u.id_user = ?1")
//    void deleteById(BigInteger id);
}
