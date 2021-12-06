package com.example.fieralinktrackerangelperez.mappers;

import com.example.fieralinktrackerangelperez.dtos.LinkRequestDTO;
import com.example.fieralinktrackerangelperez.dtos.LinkResponseDTO;
import com.example.fieralinktrackerangelperez.models.LinkStorage;

public class LinkStorageMapper {

    public static LinkStorage toLinkStorage(LinkRequestDTO linkRequestDTO)
    {
        LinkStorage linkStorage= new LinkStorage();
        linkStorage.setUrl(linkRequestDTO.getUrl());

        return linkStorage;
    }

    public static LinkResponseDTO toResponseDTO(LinkStorage linkStorage)
    {
        LinkResponseDTO linkResponseDTO= new LinkResponseDTO();
        linkResponseDTO.setLink(linkStorage.getUrl());
        linkResponseDTO.setTarget(linkStorage.getUrlAlias());

        return linkResponseDTO;
    }
}
