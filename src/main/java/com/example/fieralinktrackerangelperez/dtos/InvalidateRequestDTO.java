package com.example.fieralinktrackerangelperez.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvalidateRequestDTO {
    private String urlToInvalidate;
}
