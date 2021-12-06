package com.example.fieralinktrackerangelperez.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RedirectRequestDTO {
    private String subUrl;
    private String password;
}
