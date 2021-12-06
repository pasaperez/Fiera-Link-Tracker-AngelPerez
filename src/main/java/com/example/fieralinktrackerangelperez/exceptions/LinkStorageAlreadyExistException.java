package com.example.fieralinktrackerangelperez.exceptions;

import org.springframework.http.HttpStatus;

public class LinkStorageAlreadyExistException extends ResponseException{
    public LinkStorageAlreadyExistException() {
        super("La url/link ingresado ya existe ", HttpStatus.BAD_REQUEST);
    }
}
