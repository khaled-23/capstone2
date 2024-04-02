package com.example.capstone2.Controller;

import com.example.capstone2.ApiResponse.ApiResponse;
import com.example.capstone2.Model.ArtistPreview;
import com.example.capstone2.Service.ArtistPreviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/artist-preview")
@RequiredArgsConstructor
public class ArtistPreviewController {
    private final ArtistPreviewService artistPreviewService;

    Logger logger = LoggerFactory.getLogger(ArtistPreviewController.class);


    @PostMapping("/add")
    public ResponseEntity addPreview(@RequestBody @Valid ArtistPreview artistPreview){
        artistPreviewService.addPreview(artistPreview);
        logger.info("artist preview added");
        return ResponseEntity.ok(new ApiResponse("artist preview added"));
    }

    @GetMapping("/previews")
    public ResponseEntity getAllPreview(){
        logger.info("all previews requested");
        return ResponseEntity.ok(artistPreviewService.getAllPreviews());
    }

    @PutMapping("/update/{previewId}")
    public ResponseEntity updatePreview(@PathVariable Integer previewId, @RequestBody @Valid ArtistPreview artistPreview){
        artistPreviewService.updatePreview(previewId,artistPreview);
        logger.info("preview updated");
        return ResponseEntity.ok(new ApiResponse("preview added"));
    }

    @DeleteMapping("/delete/{previewId}")
    public ResponseEntity removePreview(@PathVariable Integer previewId){
        artistPreviewService.removePreview(previewId);
        logger.info("preview removed");
        return ResponseEntity.ok(new ApiResponse("preview removed"));
    }

    @GetMapping("/artist-previews/{artistId}")
    public ResponseEntity getArtistPreviews(@PathVariable Integer artistId){
        logger.info("previews by artist id invoked");
        return ResponseEntity.ok(artistPreviewService.getArtistPreviews(artistId));
    }

}
