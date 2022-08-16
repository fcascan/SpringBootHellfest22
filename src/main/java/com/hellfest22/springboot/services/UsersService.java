package com.hellfest22.springboot.services;

import com.hellfest22.springboot.model.User;
import com.hellfest22.springboot.repositories.UsersRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Optional;

@Service
public class UsersService implements IUsersService{
    //Atributos:
    public final UsersRepository repository;

    //Constructor:
    public UsersService(UsersRepository repository) {
        this.repository = repository;
    }

    //Metodos:
    @Override
    public Iterable<User> getAll(){
        return this.repository.findAll();
    }

    @Override
    public Iterable<User> getByEmail(String email){
        return this.repository.findByEmail(email);
    }

    @Override
    public Optional<User> getById(BigInteger id){
        return this.repository.findById(id);
    }

    @Override
    public User updateUser(User u){
        return this.repository.save(u);  //El metodo save de JPA tambien sirve para actualizar
//        return this.repository.updateById(u);
    }

    @Override
    public void deleteUser(User u){
        this.repository.delete(u);
    }
}

