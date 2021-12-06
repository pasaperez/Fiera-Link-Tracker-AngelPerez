package com.example.fieralinktrackerangelperez.services.interfaces;

import com.example.fieralinktrackerangelperez.dtos.LinkRequestDTO;
import com.example.fieralinktrackerangelperez.dtos.LinkResponseDTO;
import com.example.fieralinktrackerangelperez.exceptions.LinkStorageAlreadyExistException;
import com.example.fieralinktrackerangelperez.exceptions.UrlNotValidException;

public interface ILinkTrackerService {
    LinkResponseDTO create(LinkRequestDTO linkRequestDTO) throws LinkStorageAlreadyExistException, UrlNotValidException;
}