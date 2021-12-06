package com.example.fieralinktrackerangelperez.services;

import com.example.fieralinktrackerangelperez.dtos.*;
import com.example.fieralinktrackerangelperez.exceptions.LinkStorageAlreadyExistException;
import com.example.fieralinktrackerangelperez.exceptions.UrlInvalidException;
import com.example.fieralinktrackerangelperez.exceptions.UrlNotValidOrNotFoundException;
import com.example.fieralinktrackerangelperez.mappers.LinkStorageMapper;
import com.example.fieralinktrackerangelperez.models.LinkStorage;
import com.example.fieralinktrackerangelperez.repositories.LinkStorageRepository;
import com.example.fieralinktrackerangelperez.services.interfaces.ILinkTrackerService;
import lombok.AllArgsConstructor;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LinkTrackerService implements ILinkTrackerService {

    protected LinkStorageRepository linkStorageRepository;

    @Override
    public LinkResponseDTO create(LinkRequestDTO linkRequestDTO) throws LinkStorageAlreadyExistException, UrlNotValidOrNotFoundException {

        String[] schemes = {"http","https"};
        UrlValidator urlValidator = new UrlValidator(schemes);
        if (urlValidator.isValid(linkRequestDTO.getUrl())) {

            LinkStorage linkStorage= LinkStorageMapper.linkRequestDToLinkStorage(linkRequestDTO);

            if (!checkIfExist(linkStorage)) {
                linkStorage.setUrlAlias(randomString());
                linkStorage.setUsos(0L);
                linkStorage.setValido(true);

                return LinkStorageMapper.linkStorageToLinkResponseDTO(linkStorageRepository.save(linkStorage));
            }
            else throw new LinkStorageAlreadyExistException();
        }
        else throw new UrlNotValidOrNotFoundException();
    }

    private boolean checkIfExist(LinkStorage linkStorage) {
        List<LinkStorage> linkStorageList = linkStorageRepository.findAll();

        for(LinkStorage linkStorageIt : linkStorageList){
            if(Objects.equals(linkStorageIt.getUrl(), linkStorage.getUrl())){
                return true;
            }
        }
        return false;
    }

    private String randomString(){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 5;
        Random random = new Random();

        String s = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        StringBuilder result= new StringBuilder();
        char[] charArray = s.toCharArray();
        for (int i = 0, charArrayLength = charArray.length; i < charArrayLength; i++) {
            String temp= String.valueOf(charArray[i]);
            if (i%2==0) result.append(temp.toLowerCase());
            else result.append(temp.toUpperCase());
        }
        return result.toString();
    }

    @Override
    public ModelAndView redirect(RedirectRequestDTO redirectRequestDTO) throws UrlInvalidException, UrlNotValidOrNotFoundException {
        Optional<LinkStorage> linkStorageOptional= linkStorageRepository.findByUrlAlias(redirectRequestDTO.getSubUrl());
        if (linkStorageOptional.isPresent())
        {
            if (linkStorageOptional.get().isValido())
            {
                LinkStorage linkStorage= linkStorageOptional.get();
                linkStorage.setUsos(linkStorage.getUsos()+1);
                return new ModelAndView("redirect:"+linkStorageRepository.save(linkStorage).getUrl());
            }
            else throw new UrlInvalidException();
        }
        else throw new UrlNotValidOrNotFoundException();
    }

    @Override
    public StatResponseDTO stats(StatRequestDTO statRequestDTO) throws UrlNotValidOrNotFoundException {
        Optional<LinkStorage> linkStorageOptional= linkStorageRepository.findByUrlAlias(statRequestDTO.getUrlToStats());

        if (linkStorageOptional.isPresent()) return LinkStorageMapper.linkStorageToStatResponseDTO(linkStorageOptional.get());
        else throw new UrlNotValidOrNotFoundException();
    }

    @Override
    public Boolean invalidate(InvalidateRequestDTO invalidateRequestDTO) throws UrlNotValidOrNotFoundException {
        Optional<LinkStorage> linkStorageOptional= linkStorageRepository.findByUrlAlias(invalidateRequestDTO.getUrlToInvalidate());
        if (linkStorageOptional.isPresent())
        {
            LinkStorage linkStorage= linkStorageOptional.get();
            linkStorage.setValido(false);
            linkStorageRepository.save(linkStorage);
            return Boolean.TRUE;
        }
        else throw new UrlNotValidOrNotFoundException();
    }
}