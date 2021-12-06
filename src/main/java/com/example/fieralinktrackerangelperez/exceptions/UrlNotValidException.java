package com.example.fieralinktrackerangelperez.exceptions;

import org.springframework.http.HttpStatus;

public class UrlNotValidException extends ResponseException{
    public UrlNotValidException() {
        super("La url ingresada es invalida ", HttpStatus.BAD_REQUEST);
    }
}
