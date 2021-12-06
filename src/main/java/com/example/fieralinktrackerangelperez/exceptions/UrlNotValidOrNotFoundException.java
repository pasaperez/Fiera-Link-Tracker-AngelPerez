package com.example.fieralinktrackerangelperez.exceptions;

import org.springframework.http.HttpStatus;

public class UrlNotValidOrNotFoundException extends ResponseException{
    public UrlNotValidOrNotFoundException() {
        super("La url ingresada es invalida o no existe ", HttpStatus.BAD_REQUEST);
    }
}
