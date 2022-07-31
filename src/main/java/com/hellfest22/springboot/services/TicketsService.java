package com.hellfest22.springboot.services;
import com.hellfest22.springboot.model.Tickets;
import com.hellfest22.springboot.repositories.TicketsRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class TicketsService implements ITicketsService{
    //Atributos:
    public final TicketsRepository repository;

    //Constructor:
    public TicketsService(TicketsRepository repository) {
        this.repository = repository;
    }

    //Metodos:
    @Override
    public Tickets addTicket(Tickets t) {
        return this.repository.save(t);
    }

    @Override
    public Iterable<Tickets> getAll(){
        return this.repository.findAll();
    }

    @Override
    public Optional<Tickets> getByID(String id){
        return this.repository.findById(id);
    }

    @Override
    public Optional<Tickets> getByFullName(String name, String fname){
        return this.repository.findByFullName(name, fname);
    }
}
