package com.hellfest22.springboot.controllers;

import com.hellfest22.springboot.model.User;
import com.hellfest22.springboot.services.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
//@CrossOrigin("*")
@CrossOrigin(origins = "http://localhost:4200/")
public class UsersController {
    // API Rest para la tabla Usuarios \\
    //Atributos:
    private final UsersService servicio;

    //Constructor:
//    @Autowired
    public UsersController(UsersService servicio){
        this.servicio = servicio;
    }

    //Metodos: disponibles para ser usados por el front mediante invocacion desde url
    @GetMapping()
    public ResponseEntity<?> getAll(){
        //Obtengo todos los usuarios y los presento en una coleccion tipo List:
        List<User> uList = (List<User>) this.servicio.getAll();
        //Preparo la respuesta http:
        Map<String, Object> mensajeBody = new HashMap<>();
        if(uList.isEmpty()){
            //Respuesta http tipo Bad Request, con estado = FALSE, y en body del mensaje un aviso:
            mensajeBody.put("estado", Boolean.FALSE);
            mensajeBody.put("mensaje", "No hay datos de usuarios en la base de datos");
            return ResponseEntity.badRequest().body(mensajeBody);
        } else {
            //Respuesta http 200 = "OK", con estado = TRUE, y como body del mensaje la lista de usuarios:
            mensajeBody.put("estado", Boolean.TRUE);
            mensajeBody.put("datos", uList);
            return ResponseEntity.ok(mensajeBody);
        }
    }

    @GetMapping("/{mail}")
    public ResponseEntity<?> getByEmail(@PathVariable String mail){
        //Permite obtener todos los usuarios asociados al mail consultado.
        List<User> uList = (List<User>) this.servicio.getByEmail(mail);
        Map<String, Object> mensajeBody = new HashMap<>();
        if(uList.isEmpty()){
            mensajeBody.put("estado", Boolean.FALSE);
            mensajeBody.put("mensaje", "El usuario con mail="
                    .concat(mail).concat(" no existe"));
            return ResponseEntity.badRequest().body(mensajeBody);
        } else {
            mensajeBody.put("estado", Boolean.TRUE);
            mensajeBody.put("datos", uList);
            return ResponseEntity.ok(mensajeBody);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getByEmail(@PathVariable BigInteger id){
        //Permite obtener el usuario asociado al id consultado (solo 1 deberia existir).
        Optional<User> u = this.servicio.getById(id);
        Map<String, Object> mensajeBody = new HashMap<>();
        if(!u.isPresent()){
            mensajeBody.put("estado", Boolean.FALSE);
            mensajeBody.put("mensaje", "El usuario con id="
                    .concat(id.toString()).concat(" no existe"));
            return ResponseEntity.badRequest().body(mensajeBody);
        } else {
            mensajeBody.put("estado", Boolean.TRUE);
            mensajeBody.put("datos", u);
            return ResponseEntity.ok(mensajeBody);
        }
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<?> putUser(@PathVariable BigInteger id, @RequestBody User newU) {
        //Modifica al usuario asociado al id recibido, segun el nuevo usuario recibido en el body.
        Optional<User> oldU = this.servicio.getById(id);
        Map<String, Object> mensajeBody = new HashMap<>();
        if (!oldU.isPresent()) {
            mensajeBody.put("estado", Boolean.FALSE);
            mensajeBody.put("mensaje", "El usuario con id="
                    .concat(id.toString()).concat(" no existe"));
            return ResponseEntity.badRequest().body(mensajeBody);
        } else {
            //Creo un nuevo objeto con el usuario modificado:
            User updatedU = new User(newU.getNombre(), newU.getApellido(), newU.getRol(),
                    newU.getDireccion_de_mail(), newU.getPassword());
            updatedU.setId_user(oldU.get().getId_user());   //El id se recupera aparte ya que el constructor siempre crea uno nuevo
            mensajeBody.put("estado", Boolean.TRUE);
            mensajeBody.put("datos", this.servicio.updateUser(updatedU));
            return ResponseEntity.ok(mensajeBody);
        }
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable BigInteger id) {
        //Elimina al usuario asociado al id recibido.
        Optional<User> u = this.servicio.getById(id);
        Map<String, Object> mensajeBody = new HashMap<>();
        if (!u.isPresent()) {
            mensajeBody.put("estado", Boolean.FALSE);
            mensajeBody.put("mensaje", "El usuario con id="
                    .concat(id.toString()).concat(" no existe"));
            return ResponseEntity.badRequest().body(mensajeBody);
        } else {
            this.servicio.deleteUser(u.get());
            mensajeBody.put("estado", Boolean.TRUE);
            mensajeBody.put("datos", "Usuario eliminado exitosamente de la base de datos!");
            return ResponseEntity.ok(mensajeBody);
        }
    }
}
