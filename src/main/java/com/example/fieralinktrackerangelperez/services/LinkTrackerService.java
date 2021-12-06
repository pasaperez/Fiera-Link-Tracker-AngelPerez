package com.example.fieralinktrackerangelperez.services;

import com.example.fieralinktrackerangelperez.dtos.LinkRequestDTO;
import com.example.fieralinktrackerangelperez.dtos.LinkResponseDTO;
import com.example.fieralinktrackerangelperez.exceptions.LinkStorageAlreadyExistException;
import com.example.fieralinktrackerangelperez.exceptions.UrlNotValidException;
import com.example.fieralinktrackerangelperez.mappers.LinkStorageMapper;
import com.example.fieralinktrackerangelperez.models.LinkStorage;
import com.example.fieralinktrackerangelperez.repositories.LinkStorageRepository;
import com.example.fieralinktrackerangelperez.services.interfaces.ILinkTrackerService;
import lombok.AllArgsConstructor;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
@AllArgsConstructor
public class LinkTrackerService implements ILinkTrackerService {

    protected LinkStorageRepository linkStorageRepository;

    @Override
    public LinkResponseDTO create(LinkRequestDTO linkRequestDTO) throws LinkStorageAlreadyExistException, UrlNotValidException {

        String[] schemes = {"http","https"};
        UrlValidator urlValidator = new UrlValidator(schemes);
        if (urlValidator.isValid(linkRequestDTO.getUrl())) {

            LinkStorage linkStorage= LinkStorageMapper.toLinkStorage(linkRequestDTO);

            if (!checkIfExist(linkStorage)) {
                linkStorage.setUrlAlias(randomString());
                linkStorage.setUsos(0L);
                return LinkStorageMapper.toResponseDTO(linkStorageRepository.save(linkStorage));
            }
            else throw new LinkStorageAlreadyExistException();
        }
        else throw new UrlNotValidException();
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
        int leftLimit = 65; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 5;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}