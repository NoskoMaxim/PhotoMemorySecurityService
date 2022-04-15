package com.photomemorysecurityservice.service.fileloader;

import com.photomemorysecurityservice.dto.publication.PublicationDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PublicationService {
    void createPublication(Long UserId, MultipartFile file) throws IOException;

    PublicationDto getPublication(Long id);
}