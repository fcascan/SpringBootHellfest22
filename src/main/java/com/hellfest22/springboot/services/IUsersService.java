package com.hellfest22.springboot.services;

import com.hellfest22.springboot.model.User;

import java.math.BigInteger;
import java.util.Optional;

public interface IUsersService {
    Iterable<User> getAll();
    Iterable<User> getByEmail(String email);
    Optional<User> getById(BigInteger id);
    User updateUser(User u);
    void deleteUser(User u);
}