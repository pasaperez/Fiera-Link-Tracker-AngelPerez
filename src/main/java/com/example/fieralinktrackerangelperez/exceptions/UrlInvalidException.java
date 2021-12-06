package com.example.fieralinktrackerangelperez.exceptions;

import org.springframework.http.HttpStatus;

public class UrlInvalidException extends ResponseException{
    public UrlInvalidException() {
        super("No se puede redireccionar, la url es invalida (404)", HttpStatus.NOT_FOUND);
    }
}
