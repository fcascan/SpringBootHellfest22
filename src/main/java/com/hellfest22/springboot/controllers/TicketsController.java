package com.hellfest22.springboot.controllers;

import com.hellfest22.springboot.model.Ticket;
import com.hellfest22.springboot.services.TicketsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/tickets")
//@CrossOrigin("*")
@CrossOrigin(origins = "http://localhost:4200/")
public class TicketsController {
    // API Rest para la tabla Tickets \\
    //Atributos:
    private final TicketsService servicio;

    //Constructor:
//    @Autowired
    public TicketsController(TicketsService serv){
        this.servicio = serv;
    }

    //Metodos: disponibles para ser usados por el front por invocacion desde url
    @GetMapping("/find")
    public ResponseEntity<?> getAll(){
        //Obtengo todos los tickets mediante el servicio y los guardo en una coleccion tipo List:
        List<Ticket> tList = (List<Ticket>) this.servicio.getAll();
        //Preparo la respuesta http:
        Map<String, Object> mensajeBody = new HashMap<>();
        if(tList.isEmpty()){
            //Respuesta http tipo Bad Request, con estado = FALSE, y en body del mensaje un aviso:
            mensajeBody.put("estado", Boolean.FALSE);
            mensajeBody.put("mensaje", "No hay datos de tickets");
            return ResponseEntity.badRequest().body(mensajeBody);
        } else {
            //Respuesta http 200 = "OK", con estado = TRUE, y como body del mensaje la lista de usuarios:
            mensajeBody.put("estado", Boolean.TRUE);
            mensajeBody.put("datos", tList);
            return ResponseEntity.ok(mensajeBody);
        }
    }

    @GetMapping("/find/id/{tID}")
    public ResponseEntity<?> getById(@PathVariable String tID){
        //Permite obtener el ticket asociado al id consultado (solo 1 deberia existir).
        Optional<Ticket> t = this.servicio.getById(tID);
        Map<String, Object> mensajeBody = new HashMap<>();
        if(t.isPresent()) {
            mensajeBody.put("estado", Boolean.TRUE);
            mensajeBody.put("datos", t.get());
            return ResponseEntity.ok(mensajeBody);
        } else {
            mensajeBody.put("estado", Boolean.FALSE);
            mensajeBody.put("mensaje", "No hay datos de tickets con ese ID");
            return ResponseEntity.badRequest().body(mensajeBody);
        }
    }

    @GetMapping("/find/{name}/{fname}")
    public ResponseEntity<?> getByFullName(@PathVariable String name, @PathVariable String fname){
        //Obtengo el/los tickets solicitados segun Nombre y Apellido.
        List<Ticket> tList = (List<Ticket>) this.servicio.getByFullName(name, fname);
        Map<String, Object> mensajeBody = new HashMap<>();
        if(tList.isEmpty()){
            mensajeBody.put("estado", Boolean.FALSE);
            mensajeBody.put("mensaje", "No hay datos de tickets para esa persona");
            return ResponseEntity.badRequest().body(mensajeBody);
        } else {
            mensajeBody.put("estado", Boolean.TRUE);
            mensajeBody.put("datos", tList);
            return ResponseEntity.ok(mensajeBody);
        }
    }

    @GetMapping("/find/{email}")
    public ResponseEntity<?> getByEmail(@PathVariable String email){
        //Obtengo el/los tickets solicitados segun el mail.
        List<Ticket> tList = (List<Ticket>) this.servicio.getByEmail(email);
        Map<String, Object> mensajeBody = new HashMap<>();
        if(tList.isEmpty()){
            mensajeBody.put("estado", Boolean.FALSE);
            mensajeBody.put("mensaje", "No hay datos de tickets asociados a ese mail");
            return ResponseEntity.badRequest().body(mensajeBody);
        } else {
            mensajeBody.put("estado", Boolean.TRUE);
            mensajeBody.put("datos", tList);
            return ResponseEntity.ok(mensajeBody);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addTicket(@RequestBody Ticket t){
        //Agrega un nuevo ticket a la base de datos.
        //Primero creo un nuevo objeto Ticket con la info recibida y el resto de sus atributos los crea el constructor:
        Ticket newT = new Ticket(t.getTipoDeTicket(), t.getCantidadDeTickets(),
                                   t.getNombre(), t.getApellido(),
                                   t.getDireccionDeMail(), t.getTelefono(), t.getMetodoDePago());
        //Preparo la respuesta http a la vez que solicito al servicio que agregue el ticket recibido en el body del request:
        Map<String, Object> mensajeBody = new HashMap<>();
        mensajeBody.put("estado", Boolean.TRUE);
        mensajeBody.put("datos", this.servicio.addTicket(newT).getIdCompra());
        //Solo se retorna el ID de Compra generado:
        return new ResponseEntity<>(mensajeBody, HttpStatus.CREATED);
    }

    @PutMapping("/find/id/{tId}")
    public ResponseEntity<?> putUser(@PathVariable String tId, @RequestBody Ticket newT) {
        //Modifica un ticket asociado al id recibido, segun el nuevo ticket recibido en el body.
        Optional<Ticket> oldT = this.servicio.getById(tId);
        Map<String, Object> mensajeBody = new HashMap<>();
        if (!oldT.isPresent()) {
            mensajeBody.put("estado", Boolean.FALSE);
            mensajeBody.put("mensaje", "El ticket con id="
                    .concat(tId).concat(" no existe"));
            return ResponseEntity.badRequest().body(mensajeBody);
        } else {
            //Creo un nuevo objeto con el ticket modificado:
            Ticket updatedT = new Ticket(newT.getTipoDeTicket(), newT.getCantidadDeTickets(), newT.getNombre(), newT.getApellido(),
                                         newT.getDireccionDeMail(), newT.getTelefono(), newT.getMetodoDePago());
            updatedT.setIdCompra(oldT.get().getIdCompra());   //El id se recupera aparte ya que el constructor siempre crea uno nuevo
            mensajeBody.put("estado", Boolean.TRUE);
            mensajeBody.put("datos", this.servicio.updateTicket(updatedT));
            return ResponseEntity.ok(mensajeBody);
        }
    }

    @DeleteMapping("/find/id/{tId}")
    public ResponseEntity<?> deleteTicket(@PathVariable String tId) {
        //Elimina el ticket asociado al id recibido.
        Optional<Ticket> t = this.servicio.getById(tId);
        Map<String, Object> mensajeBody = new HashMap<>();
        if (!t.isPresent()) {
            mensajeBody.put("estado", Boolean.FALSE);
            mensajeBody.put("mensaje", "El ticket con id="
                    .concat(tId).concat(" no existe"));
            return ResponseEntity.badRequest().body(mensajeBody);
        } else {
            this.servicio.deleteTicket(t.get());
            mensajeBody.put("estado", Boolean.TRUE);
            mensajeBody.put("datos", "Ticket eliminado exitosamente de la base de datos!");
            return ResponseEntity.ok(mensajeBody);
        }
    }
}
