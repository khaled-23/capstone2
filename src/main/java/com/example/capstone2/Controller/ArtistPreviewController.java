package com.example.capstone2.Controller;

import com.example.capstone2.ApiResponse.ApiResponse;
import com.example.capstone2.Model.ArtistPreview;
import com.example.capstone2.Service.ArtistPreviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/artist-preview")
@RequiredArgsConstructor
public class ArtistPreviewController {
    private final ArtistPreviewService artistPreviewService;



    @PostMapping("/add")
    public ResponseEntity addPreview(@RequestBody @Valid ArtistPreview artistPreview, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        artistPreviewService.addPreview(artistPreview);
        return ResponseEntity.ok(new ApiResponse("artist preview added"));
    }

    @GetMapping("/previews")
    public ResponseEntity getAllPreview(){
        return ResponseEntity.ok(artistPreviewService.getAllPreviews());
    }

    @PutMapping("/update/{previewId}")
    public ResponseEntity updatePreview(@PathVariable Integer previewId, @RequestBody @Valid ArtistPreview artistPreview, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        artistPreviewService.updatePreview(previewId,artistPreview);
        return ResponseEntity.ok(new ApiResponse("preview added"));
    }

    @DeleteMapping("/delete/{previewId}")
    public ResponseEntity removePreview(@PathVariable Integer previewId){
        artistPreviewService.removePreview(previewId);
        return ResponseEntity.ok(new ApiResponse("preview removed"));
    }
}
