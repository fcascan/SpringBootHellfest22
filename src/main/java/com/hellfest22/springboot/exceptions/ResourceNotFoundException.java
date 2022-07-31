package com.hellfest22.springboot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    //Clase Personalizada de ErrorException de ID no encontrado
    private static final String serialVersionID = "XXXX0000000000";

    public ResourceNotFoundException(String mje){
        super(mje);
    }
}
