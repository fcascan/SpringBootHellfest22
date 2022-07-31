package com.hellfest22.springboot.controllers;
import com.hellfest22.springboot.model.Tickets;
import com.hellfest22.springboot.services.TicketsService;
//import org.springframework.beans.factory.annotation.Autowired;
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
    // API Rest para los Tickets \\
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
        List<Tickets> tList = (List<Tickets>) this.servicio.getAll();
        //Preparo la respuesta http:
        Map<String, Object> mensajeBody = new HashMap<>();
        if(tList.isEmpty()){
            mensajeBody.put("estado", Boolean.FALSE);
            mensajeBody.put("mensaje", "No hay datos de tickets");
            return ResponseEntity
                    .badRequest()
                    .body(mensajeBody);
        }
        mensajeBody.put("estado", Boolean.TRUE);
        mensajeBody.put("datos", tList);
        return ResponseEntity.ok(mensajeBody);
    }

    @GetMapping("/find/{tID}")
    public ResponseEntity<?> getById(@PathVariable String tID){
        //Obtengo el ticket solicitado:
        Optional<Tickets> t = this.servicio.getByID(tID);
        //Preparo la respuesta http:
        Map<String, Object> mensajeBody = new HashMap<>();
        mensajeBody.put("estado", Boolean.TRUE);
        mensajeBody.put("datos", t.get());
        return ResponseEntity.ok(mensajeBody);
    }

    @GetMapping("/find/{name}/fname")
    public ResponseEntity<?> getByFullName(@PathVariable String name, @PathVariable String fname){
        //Obtengo el/los tickets solicitados:
        Optional<Tickets> t = this.servicio.getByFullName(name, fname);
        //Preparo la respuesta http:
        Map<String, Object> mensajeBody = new HashMap<>();
        mensajeBody.put("estado", Boolean.TRUE);
        mensajeBody.put("datos", t.get());
        return ResponseEntity.ok(mensajeBody);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addTicket(@RequestBody Tickets t){
        //Primero creo un nuevo objeto Ticket con la info recibida y el resto de sus atributos los crea el constructor:
        Tickets newT = new Tickets(t.getTipoDeTicket(), t.getCantidadDeTickets(),
                                   t.getNombre(), t.getApellido(),
                                   t.getDireccionDeMail(), t.getTelefono(), t.getMetodoDePago());
        //Preparo la respuesta http a la vez que solicito al servicio que agregue el ticket recibido en el body del request:
        Map<String, Object> mensajeBody = new HashMap<>();
        mensajeBody.put("estado", Boolean.TRUE);
        mensajeBody.put("datos", this.servicio.addTicket(newT).getIdCompra());
        //Solo se retorna el ID de Compra generado:
        return new ResponseEntity<>(mensajeBody, HttpStatus.CREATED);
    }
}
