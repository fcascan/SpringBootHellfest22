package com.hellfest22.springboot.services;

import com.hellfest22.springboot.model.User;
import com.hellfest22.springboot.repositories.RegisterRepository;
import com.hellfest22.springboot.repositories.TicketsRepository;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    //Atributos:
    public final RegisterRepository repository;

    //Constructor:
    public RegisterService(RegisterRepository repository) {
        this.repository = repository;
    }

    //Metodos:
    public User addUser(User u){
        return this.repository.save(u);
    }

}
