package com.hellfest22.springboot.services;

import com.hellfest22.springboot.model.Login;

import java.math.BigInteger;
import java.util.Optional;

public interface ILoginService {
    Iterable<Login> getAll();
    Login findLoginBy(String mail, String pass, String role);
}
