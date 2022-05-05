package com.photomemorysecurityservice.controller.publication;

import com.photomemorysecurityservice.dto.OperationMessageDto;
import com.photomemorysecurityservice.dto.publication.CommentForAddingToPublicationDto;
import com.photomemorysecurityservice.dto.publication.PublicationDto;
import com.photomemorysecurityservice.dto.publication.TextForCreatePublicationDto;
import com.photomemorysecurityservice.service.publication.PublicationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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
            path = "/{userId}",
            consumes = MULTIPART_FORM_DATA_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<OperationMessageDto> uploadPhotoForPublication(
            @PathVariable(value = "userId") Long userId,
            @RequestPart("file") MultipartFile file) throws IOException {
        publicationService.createPublication(userId, file);
        return ResponseEntity.ok(new OperationMessageDto("Successful operation"));
    }

    @GetMapping(path = "/get/photo/{photoId}",
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PublicationDto> getPublication(
            @PathVariable Long photoId) {
        return ResponseEntity.ok().body(publicationService.getPublication(photoId));
    }

    @GetMapping(path = "/get/all",
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PublicationDto>> getAllPublications() {
        return ResponseEntity.ok().body(publicationService.getAllPublications());
    }

    @PostMapping(path = "/set/text",
            consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<OperationMessageDto> setTextForPublication(
            @RequestBody TextForCreatePublicationDto textDto) {
        publicationService.setTextForPublication(textDto);
        return ResponseEntity.ok(new OperationMessageDto("Successful operation"));
    }

    @GetMapping(path = "/get/{userId}",
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PublicationDto>> getAllPublicationByUserWithUserId(
            @PathVariable Long userId) {
        return ResponseEntity.ok().body(publicationService.getAllPublicationByUserWithUserId(userId));
    }

    @PostMapping(path = "/set/comment",
            consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<OperationMessageDto> setCommentForPublication(
            @RequestBody CommentForAddingToPublicationDto commentDto) {
        publicationService.setCommentForPublication(commentDto);
        return ResponseEntity.ok(new OperationMessageDto("Successful operation"));
    }

    @DeleteMapping(path ="/delete/{publicationId}")
    public ResponseEntity<OperationMessageDto> deletePublicationById(
            @PathVariable Long publicationId){
        publicationService.deletePublicationById(publicationId);
        return ResponseEntity.ok(new OperationMessageDto("Successful operation"));
    }
}