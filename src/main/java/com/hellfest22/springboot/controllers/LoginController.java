package com.hellfest22.springboot.controllers;

import com.hellfest22.springboot.model.Login;
import com.hellfest22.springboot.services.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/login")
//@CrossOrigin("*")
@CrossOrigin(origins = "http://localhost:4200/")
public class LoginController {
    // API Rest para el Login de usuarios \\
    //Atributos:
    private final LoginService servicio;

    //Constructor:
//    @Autowired
    public LoginController(LoginService serv){
        this.servicio = serv;
    }

    //Metodos: disponibles para ser usados por el front por invocacion desde url
    @GetMapping("/find")
    public ResponseEntity<?> getAll(){
        //Obtengo todos las credenciales de Usuarios mediante el servicio y los guardo en una coleccion tipo List:
        List<Login> lList = (List<Login>) this.servicio.getAll();
        //Preparo la respuesta http:
        Map<String, Object> mensajeBody = new HashMap<>();
        if(lList.isEmpty()){
            mensajeBody.put("estado", Boolean.FALSE);
            mensajeBody.put("mensaje", "No hay datos de usuarios");
            return ResponseEntity
                    .badRequest()
                    .body(mensajeBody);
        }
        mensajeBody.put("estado", Boolean.TRUE);
        mensajeBody.put("datos", lList);
        return ResponseEntity.ok(mensajeBody);
    }

    @PostMapping("/find/user")
    public ResponseEntity<?> findUser(@RequestBody Login l){
        //Se reciben las credenciales de mail, password y rol en el body, y se retorna el resto de la info del usuario en la DB:
        Map<String, Object> mensajeBody = new HashMap<>();
        HttpStatus hS;
        Login lFind = new Login();
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
