package com.example.fieralinktrackerangelperez.mappers;

import com.example.fieralinktrackerangelperez.dtos.LinkRequestDTO;
import com.example.fieralinktrackerangelperez.dtos.LinkResponseDTO;
import com.example.fieralinktrackerangelperez.dtos.StatRequestDTO;
import com.example.fieralinktrackerangelperez.dtos.StatResponseDTO;
import com.example.fieralinktrackerangelperez.models.LinkStorage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("HttpUrlsUsage")
public class LinkStorageMapper {

    private static String serverUrl;
    private static String port;

    @Value("${server.address}")
    public void setServerUrl(String value) {
        serverUrl = value;
    }
    @Value("${server.port}")
    public void setPort(String value) {
        port = value;
    }

    public static LinkStorage linkRequestDToLinkStorage(LinkRequestDTO linkRequestDTO)
    {
        LinkStorage linkStorage= new LinkStorage();
        linkStorage.setUrl(linkRequestDTO.getUrl());

        return linkStorage;
    }

    public static LinkResponseDTO linkStorageToLinkResponseDTO(LinkStorage linkStorage)
    {
        LinkResponseDTO linkResponseDTO= new LinkResponseDTO();
        linkResponseDTO.setLink(linkStorage.getUrl());
        linkResponseDTO.setTarget("http://" + serverUrl + ":" + port +"/I/" + linkStorage.getUrlAlias());
        linkResponseDTO.setValid(linkStorage.isValido());

        return linkResponseDTO;
    }

    public static StatResponseDTO linkStorageToStatResponseDTO(LinkStorage linkStorage)
    {
        StatResponseDTO statResponseDTO= new StatResponseDTO();
        statResponseDTO.setUsos(linkStorage.getUsos());
        statResponseDTO.setValid(linkStorage.isValido());
        return statResponseDTO;
    }
}
