package com.example.capstone2.Controller;

import com.example.capstone2.ApiResponse.ApiResponse;
import com.example.capstone2.Model.ArtistThread;
import com.example.capstone2.Service.ArtistThreadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class ArtistThreadController {

    private final ArtistThreadService artistThreadService;

    @GetMapping("/threads")
    public ResponseEntity getAllArtistThreads(){
        return ResponseEntity.ok(artistThreadService.getAllArtistThreads());
    }

    @PostMapping("/add")
    public ResponseEntity addArtistThread(@RequestBody @Valid ArtistThread artistThread, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        artistThreadService.addArtistThread(artistThread);
        return ResponseEntity.ok(new ApiResponse("artistThread added"));
    }

    @PutMapping("/update/{artistThreadId}")
    public ResponseEntity updateArtistThread(@PathVariable Integer artistThreadId, @RequestBody @Valid ArtistThread artistThread, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        artistThreadService.updateArtistThread(artistThreadId,artistThread);
        return ResponseEntity.ok(new ApiResponse("artistThread updated"));
    }

    @DeleteMapping("/remove/{artistThreadId}")
    public ResponseEntity removeArtistThread(@PathVariable Integer artistThreadId){
        artistThreadService.removeArtistThread(artistThreadId);
        return ResponseEntity.ok(new ApiResponse("artistThread removed"));
    }

    @PutMapping("/pin/{adminId}/{artistThreadId}/{choice}")
    public ResponseEntity pinImportantThread(@PathVariable Integer adminId, @PathVariable Integer artistThreadId,@PathVariable Boolean choice){
        artistThreadService.pinThread(adminId,artistThreadId,choice);
        return ResponseEntity.ok(new ApiResponse("is thread pinned: "+choice));
    }

    @GetMapping("/pinned-threads")
    public ResponseEntity getAllPinnedThread() {
        return ResponseEntity.ok(artistThreadService.getAllImportantThread());
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity searchForThreads(@PathVariable String keyword){
        return ResponseEntity.ok(artistThreadService.searchArtistThreads(keyword));
    }



}
