package com.example.capstone2.Controller;

import com.example.capstone2.ApiResponse.ApiResponse;
import com.example.capstone2.Model.ArtistThread;
import com.example.capstone2.Service.ArtistThreadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class ArtistThreadController {

    private final ArtistThreadService artistThreadService;
    Logger logger = LoggerFactory.getLogger(ArtistThreadController.class);

    @GetMapping("/threads")
    public ResponseEntity getAllArtistThreads(){
        logger.info("thread invoked");
        return ResponseEntity.ok(artistThreadService.getAllArtistThreads());
    }

    @PostMapping("/add")
    public ResponseEntity addArtistThread(@RequestBody @Valid ArtistThread artistThread){
        artistThreadService.addArtistThread(artistThread);
        logger.info("thread added");
        return ResponseEntity.ok(new ApiResponse("artistThread added"));
    }

    @PutMapping("/update/{artistThreadId}")
    public ResponseEntity updateArtistThread(@PathVariable Integer artistThreadId, @RequestBody @Valid ArtistThread artistThread, Errors errors){
        artistThreadService.updateArtistThread(artistThreadId,artistThread);
        logger.info("thread updated");
        return ResponseEntity.ok(new ApiResponse("artistThread updated"));
    }

    @DeleteMapping("/remove/{artistThreadId}")
    public ResponseEntity removeArtistThread(@PathVariable Integer artistThreadId){
        artistThreadService.removeArtistThread(artistThreadId);
        logger.info("thread removed");
        return ResponseEntity.ok(new ApiResponse("artistThread removed"));
    }

    @PutMapping("/pin/{adminId}/{artistThreadId}/{choice}")
    public ResponseEntity pinImportantThread(@PathVariable Integer adminId, @PathVariable Integer artistThreadId,@PathVariable Boolean choice){
        artistThreadService.pinThread(adminId,artistThreadId,choice);
        logger.info("an admin pinned an important thread");
        return ResponseEntity.ok(new ApiResponse("is thread pinned: "+choice));
    }

    @GetMapping("/pinned-threads")
    public ResponseEntity getAllPinnedThread() {
        logger.info("pinned threads requested");
        return ResponseEntity.ok(artistThreadService.getAllImportantThread());
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity searchForThreads(@PathVariable String keyword){
        logger.info("search for thread requested");
        return ResponseEntity.ok(artistThreadService.searchArtistThreads(keyword));
    }



}
