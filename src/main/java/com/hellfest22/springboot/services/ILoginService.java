package com.hellfest22.springboot.services;

import com.hellfest22.springboot.model.User;

public interface ILoginService {
    User findLoginBy(String mail, String pass, String role);
}
