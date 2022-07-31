package com.hellfest22.springboot.services;
import com.hellfest22.springboot.model.Login;
import com.hellfest22.springboot.repositories.LoginRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
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
    @Override
    public Iterable<Login> getAll(){
        return this.repository.findAll();
    }

    @Override
    public Login findLoginBy(String mail, String pass, String role){
        Optional<Login> l = this.repository.findLoginBy(mail, pass, role);
        if(l.isPresent()) {
            Login newL = new Login();
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
