package com.example.fieralinktrackerangelperez.services.interfaces;

import com.example.fieralinktrackerangelperez.dtos.*;
import com.example.fieralinktrackerangelperez.exceptions.LinkStorageAlreadyExistException;
import com.example.fieralinktrackerangelperez.exceptions.UrlInvalidException;
import com.example.fieralinktrackerangelperez.exceptions.UrlNotValidOrNotFoundException;
import org.springframework.web.servlet.ModelAndView;

public interface ILinkTrackerService {
    LinkResponseDTO create(LinkRequestDTO linkRequestDTO) throws LinkStorageAlreadyExistException, UrlNotValidOrNotFoundException;
    ModelAndView redirect(RedirectRequestDTO redirectRequestDTO) throws UrlInvalidException, UrlNotValidOrNotFoundException;
    StatResponseDTO stats(StatRequestDTO statRequestDTO) throws UrlNotValidOrNotFoundException;
    Boolean invalidate(InvalidateRequestDTO invalidateRequestDTO) throws UrlNotValidOrNotFoundException;
}