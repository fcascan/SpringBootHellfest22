package com.hellfest22.springboot.controllers;

import com.hellfest22.springboot.model.User;
import com.hellfest22.springboot.services.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/login")
//@CrossOrigin("*")
@CrossOrigin(origins = "http://localhost:4200/")
public class LoginController {
    // API Rest para el control de Login de usuarios \\
    //Atributos:
    private final LoginService servicio;

    //Constructor:
//    @Autowired
    public LoginController(LoginService serv){
        this.servicio = serv;
    }

    //Metodos: disponibles para ser usados por el front por invocacion desde url
    @PostMapping()
    public ResponseEntity<?> findUser(@RequestBody User l){
        //Se reciben las credenciales de mail, password y rol en el body, y se retorna el resto de la info del usuario en la DB:
        Map<String, Object> mensajeBody = new HashMap<>();
        HttpStatus hS;
        User lFind = new User();
        lFind = this.servicio.findLoginBy(l.getDireccion_de_mail(), l.getPassword(), l.getRol());
        if(lFind !=null){
            mensajeBody.put("estado", Boolean.TRUE);
            hS = HttpStatus.OK;
        } else {
            mensajeBody.put("estado", Boolean.FALSE);
            hS = HttpStatus.NOT_FOUND;
        }
        mensajeBody.put("datos", lFind);
        //Solo se retorna el usuario (con su clave censurada):
        return new ResponseEntity<>(mensajeBody, hS);
    }
}
