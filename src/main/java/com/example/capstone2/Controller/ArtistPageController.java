package com.example.capstone2.Controller;

import com.example.capstone2.ApiResponse.ApiResponse;
import com.example.capstone2.Model.ArtistPage;
import com.example.capstone2.Service.ArtistPageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/artist-page")
@RequiredArgsConstructor
public class ArtistPageController {
    private final ArtistPageService artistPageService;


    @PostMapping("/add")
    public ResponseEntity addArtistPage(@RequestBody @Valid ArtistPage artistPage, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        artistPageService.addArtistPage(artistPage);
        return ResponseEntity.ok(new ApiResponse("artist page added"));
    }

    @GetMapping("/artist-pages")
    public ResponseEntity getAllArtistPage(){
        return ResponseEntity.ok(artistPageService.getAllArtistPage());
    }

    @PutMapping("/update/{artistPageId}")
    public ResponseEntity updateArtistPage(@PathVariable Integer artistPageId, @RequestBody @Valid ArtistPage artistPage, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        artistPageService.updateArtistPage(artistPageId, artistPage);
        return ResponseEntity.ok(new ApiResponse("artist page updated"));
    }

    @DeleteMapping("/remove/{artistPageId}")
    public ResponseEntity removeArtistPage(@PathVariable Integer artistPageId){
        artistPageService.removeArtistPage(artistPageId);
        return ResponseEntity.ok(new ApiResponse("artist page removed"));
    }

    @GetMapping("/get-available-artists")
    public ResponseEntity availableArtist(){
        return ResponseEntity.ok(artistPageService.getAllPagesWhereArtistAcceptOrder());
    }


}
