package com.hellfest22.springboot.controllers;

import com.hellfest22.springboot.model.User;
import com.hellfest22.springboot.services.RegisterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/register")
//@CrossOrigin("*")
@CrossOrigin(origins = "http://localhost:4200/")
public class RegisterController {
    // API Rest para el registro de nuevos usuarios \\
    //Atributos:
    private final RegisterService servicio;

    //Constructor:
    public RegisterController(RegisterService serv)
    {
        this.servicio = serv;
    }

    //Metodos:
    @PostMapping
    public ResponseEntity<?> addTicket(@RequestBody User u, BindingResult br){
        //Agrega/Registra a un nuevo usuario a la base de datos.
        //Primero creo un nuevo objeto User con la info recibida y el resto de sus atributos se encarga su constructor:
        User newU = new User(u.getNombre(), u.getApellido(), u.getRol(), u.getDireccion_de_mail(), u.getPassword());
        if(br.hasErrors()){
            Map<String, Object> validacion = new HashMap<>();
            br.getFieldErrors()
                    .forEach(error -> validacion.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(validacion);
        }
        //Preparo la respuesta http a la vez que solicito al servicio que agregue al usuario:
        Map<String, Object> mensajeBody = new HashMap<>();
        mensajeBody.put("estado", Boolean.TRUE);
        mensajeBody.put("datos", this.servicio.addUser(newU).getId_user());
        //Solo se retorna el ID del nuevo usuario generado:
        return new ResponseEntity<>(mensajeBody, HttpStatus.CREATED);
    }
}
