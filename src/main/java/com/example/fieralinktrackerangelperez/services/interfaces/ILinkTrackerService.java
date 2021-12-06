package com.example.fieralinktrackerangelperez.services.interfaces;

import com.example.fieralinktrackerangelperez.dtos.*;
import com.example.fieralinktrackerangelperez.exceptions.LinkStorageAlreadyExistException;
import com.example.fieralinktrackerangelperez.exceptions.UrlInvalidException;
import com.example.fieralinktrackerangelperez.exceptions.UrlNotValidException;
import org.springframework.web.servlet.ModelAndView;

public interface ILinkTrackerService {
    LinkResponseDTO create(LinkRequestDTO linkRequestDTO) throws LinkStorageAlreadyExistException, UrlNotValidException;
    ModelAndView redirect(RedirectRequestDTO redirectRequestDTO) throws UrlInvalidException;
    StatResponseDTO stats(StatRequestDTO statRequestDTO) throws UrlNotValidException;
}