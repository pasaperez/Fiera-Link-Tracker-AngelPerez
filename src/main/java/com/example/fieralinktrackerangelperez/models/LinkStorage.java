package com.example.fieralinktrackerangelperez.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class LinkStorage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLinkStorage;
    private String url;
    @Column(unique = true)
    private String urlAlias;
    private Long usos;
    private boolean valido;
    private String password;
}