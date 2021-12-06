package com.example.fieralinktrackerangelperez.controllers;

import com.example.fieralinktrackerangelperez.dtos.*;
import com.example.fieralinktrackerangelperez.exceptions.LinkStorageAlreadyExistException;
import com.example.fieralinktrackerangelperez.exceptions.UrlInvalidException;
import com.example.fieralinktrackerangelperez.exceptions.UrlNotValidOrNotFoundException;
import com.example.fieralinktrackerangelperez.services.interfaces.ILinkTrackerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/")
public class LinkTrackerController {

    protected ILinkTrackerService iLinkTrackerService;

    @PostMapping(path = "create", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<LinkResponseDTO> create(LinkRequestDTO linkRequestDTO) throws LinkStorageAlreadyExistException, UrlNotValidOrNotFoundException {
        return new ResponseEntity<>(iLinkTrackerService.create(linkRequestDTO), HttpStatus.OK);
    }

    @GetMapping(path = "I/{subUrl}")
    public ModelAndView redirect(@PathVariable @Validated String subUrl, @RequestParam(required = false) @Validated String password) throws UrlInvalidException, UrlNotValidOrNotFoundException {
        RedirectRequestDTO redirectRequestDTO = new RedirectRequestDTO();
        redirectRequestDTO.setSubUrl(subUrl);
        redirectRequestDTO.setPassword(password==null ? "" : password);
        return iLinkTrackerService.redirect(redirectRequestDTO);
    }

    @GetMapping(path = "stat/{subUrl}")
    public ResponseEntity<StatResponseDTO> stats(@PathVariable @Validated String subUrl) throws UrlNotValidOrNotFoundException {
        StatRequestDTO statRequestDTO= new StatRequestDTO();
        statRequestDTO.setUrlToStats(subUrl);
        return new ResponseEntity<>(iLinkTrackerService.stats(statRequestDTO), HttpStatus.OK);
    }

    @PutMapping(path = "invalidate/{subUrl}")
    public ResponseEntity<Boolean> invalidate(@PathVariable @Validated String subUrl) throws UrlNotValidOrNotFoundException {
        InvalidateRequestDTO invalidateRequestDTO= new InvalidateRequestDTO();
        invalidateRequestDTO.setUrlToInvalidate(subUrl);
        return new ResponseEntity<>(iLinkTrackerService.invalidate(invalidateRequestDTO), HttpStatus.OK);
    }
}