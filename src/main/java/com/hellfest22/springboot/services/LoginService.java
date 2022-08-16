package com.hellfest22.springboot.services;
import com.hellfest22.springboot.model.User;
import com.hellfest22.springboot.repositories.LoginRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService implements ILoginService{
    //Atributos:
    public final LoginRepository repository;

    //Constructor:
    public LoginService(LoginRepository repository) {
        this.repository = repository;
    }

    //Metodos:
//    @Override
//    public Iterable<User> getAll(){
//        return this.repository.findAll();
//    }

    @Override
    public User findLoginBy(String mail, String pass, String role){
        Optional<User> l = this.repository.findLoginBy(mail, pass, role);
        if(l.isPresent()) {
            User newL = new User();
            newL.setId_user(l.get().getId_user());
            newL.setNombre(l.get().getNombre());
            newL.setApellido(l.get().getApellido());
            newL.setRol(l.get().getRol());
            newL.setDireccion_de_mail(l.get().getDireccion_de_mail());
            newL.setPassword("******");
            return newL;
        } else {
            return null;
        }
    }
}
