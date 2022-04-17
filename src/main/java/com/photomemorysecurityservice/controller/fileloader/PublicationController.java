package com.photomemorysecurityservice.controller.fileloader;

import com.photomemorysecurityservice.dto.OperationMessageDto;
import com.photomemorysecurityservice.dto.publication.PublicationDto;
import com.photomemorysecurityservice.service.fileloader.PublicationService;
import com.photomemorysecurityservice.service.fileloader.PublicationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("/photomemory/publication")
public class PublicationController {

    private final PublicationServiceImpl publicationService;

    @Autowired
    public PublicationController(PublicationServiceImpl publicationService) {
        this.publicationService = publicationService;
    }

    @PostMapping(
            path = "/file/{userId}",
            consumes = MULTIPART_FORM_DATA_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<OperationMessageDto> uploadPhotoForPublication(
            @PathVariable(value = "userId") Long userId,
            @RequestPart("file") MultipartFile file) throws IOException {
        publicationService.createPublication(userId, file);
        return ResponseEntity.ok(new OperationMessageDto("Successful operation"));
    }

    @GetMapping(path = "/get/{photoId}",
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PublicationDto> getPublication(
            @PathVariable Long photoId) {
        return ResponseEntity.ok().body(publicationService.getPublication(photoId));
    }
}