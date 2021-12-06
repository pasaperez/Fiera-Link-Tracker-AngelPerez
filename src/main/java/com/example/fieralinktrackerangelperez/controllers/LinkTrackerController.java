package com.example.fieralinktrackerangelperez.controllers;

import com.example.fieralinktrackerangelperez.dtos.LinkRequestDTO;
import com.example.fieralinktrackerangelperez.dtos.LinkResponseDTO;
import com.example.fieralinktrackerangelperez.exceptions.LinkStorageAlreadyExistException;
import com.example.fieralinktrackerangelperez.exceptions.UrlNotValidException;
import com.example.fieralinktrackerangelperez.services.interfaces.ILinkTrackerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/")
public class LinkTrackerController {

    protected ILinkTrackerService iLinkTrackerService;

    @PostMapping(path = "create")
    public ResponseEntity<LinkResponseDTO> create(@RequestParam @Validated String url) throws LinkStorageAlreadyExistException, UrlNotValidException {
        return new ResponseEntity<>(iLinkTrackerService.create(new LinkRequestDTO(url)), HttpStatus.OK);
    }

}