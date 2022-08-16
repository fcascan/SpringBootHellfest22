package com.hellfest22.springboot.services;
import com.hellfest22.springboot.model.Ticket;
import com.hellfest22.springboot.repositories.TicketsRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class TicketsService implements ITicketsService{
    //Atributos:
    public final TicketsRepository repository;

    //Constructor:
//    @Autowired
    public TicketsService(TicketsRepository repository) {
        this.repository = repository;
    }

    //Metodos:
    @Override
    public Iterable<Ticket> getAll(){
        return this.repository.findAll();
    }

    @Override
    public Optional<Ticket> getById(String id){
        return this.repository.findById(id);
    }

    @Override
    public Iterable<Ticket> getByFullName(String name, String fname){
        return this.repository.findByFullName(name, fname);
    }

    @Override
    public Iterable<Ticket> getByEmail(String email){
        return this.repository.findByEmail(email);
    }

    @Override
    public Ticket addTicket(Ticket t) {
        return this.repository.save(t);
    }

    @Override
    public Ticket updateTicket(Ticket t){
        return this.repository.save(t);     //El metodo save de JPA tambien sirve para actualizar
    }

    @Override
    public void deleteTicket(Ticket t){
        this.repository.delete(t);
    }
}
