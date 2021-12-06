package com.example.fieralinktrackerangelperez.exceptions;

import com.example.fieralinktrackerangelperez.dtos.ErrorDTO;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseException extends Exception{
    private ErrorDTO error;
    private HttpStatus status;

    public ResponseException(String message, HttpStatus status){
        this.error = new ErrorDTO();
        this.error.setMessage(message);
        this.error.setName(this.getClass().getSimpleName());
        this.status = status;
    }
}