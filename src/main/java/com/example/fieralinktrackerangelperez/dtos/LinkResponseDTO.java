package com.example.fieralinktrackerangelperez.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LinkResponseDTO {
    private String target;
    private String link;
    private boolean valid;
}
