package com.example.capstone2.Controller;

import com.example.capstone2.ApiResponse.ApiResponse;
import com.example.capstone2.Model.Artist;
import com.example.capstone2.Service.ArtistService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/artist")
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistService artistService;

    Logger logger = LoggerFactory.getLogger(ArtistController.class);

    @PostMapping("/add")
    public ResponseEntity addArtist(@RequestBody @Valid Artist artist){
        artistService.addArtist(artist);
        logger.info("artist added");
        return ResponseEntity.ok(new ApiResponse("artist added"));
    }

    @GetMapping("/artists")
    public ResponseEntity getAllArtist(){
        logger.info("get all artist requested");
        return ResponseEntity.ok(artistService.getAllArtist());
    }

    @PutMapping("/update/{artistId}")
    public ResponseEntity updateArtist(@PathVariable Integer artistId,@RequestBody @Valid Artist artist) {
        artistService.updateArtist(artistId, artist);
        logger.info("artist Updated");
        return ResponseEntity.ok("artist updated");
    }

    @DeleteMapping("/remove/{artistId}")
    public ResponseEntity removeArtist(@PathVariable Integer artistId){
        artistService.removeArtist(artistId);
        logger.info("artist updated");
        return ResponseEntity.ok("artist removed");
    }

    @GetMapping("/orders/{artistId}")
    public ResponseEntity getAllArtistOrder(@PathVariable Integer artistId){
        logger.info("artist requested all his order");
        return ResponseEntity.ok(artistService.getOrdersByArtistId(artistId));
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity searchForArtist(@PathVariable String keyword){
        logger.info("get list of artists by artist name or description keyword:"+ keyword);
        return ResponseEntity.ok(artistService.searchForArtist(keyword));
    }

    @GetMapping("/login/{username}/{password}")
    public ResponseEntity login(@PathVariable String username, @PathVariable String password){
        return ResponseEntity.ok(artistService.loginCheck(username,password));
    }

    @PutMapping("/take-orders/{artistId}/{choice}")
    public ResponseEntity changeTakeOrder(@PathVariable Integer artistId,@PathVariable Boolean choice){
        artistService.setAcceptOrder(artistId, choice);
        logger.info("artist changed doesAcceptOrder");
        return ResponseEntity.ok(new ApiResponse("take-orders changed to: "+choice));
    }

    @PutMapping("/complete-order/{artistId}/{orderId}")
    public ResponseEntity setOrderAsDone(@PathVariable Integer artistId, @PathVariable Integer orderId){
        artistService.setOrderAsDone(artistId, orderId);
        logger.info("artist completed order");
        return ResponseEntity.ok(new ApiResponse("order status changed to done"));
    }

    @GetMapping("order-notes/{artistId}/{orderId}")
    public ResponseEntity getAllNoteByOrderId(@PathVariable Integer artistId, @PathVariable Integer orderId){
        logger.info("note added to order");
        return ResponseEntity.ok(artistService.getAllNoteByOrderId(artistId,orderId));
    }

    @GetMapping("/get-available-artists")
    public ResponseEntity availableArtist(){
        logger.info("all available artist requested");
        return ResponseEntity.ok(artistService.getAllArtistWhereArtistAcceptOrder());
    }

    @GetMapping("/average-rate/{artistId}")
    public ResponseEntity getAverageRate(@PathVariable Integer artistId){
        logger.info("calculate average rate for an artist");
        return ResponseEntity.ok(artistService.artistAverageRate(artistId));
    }

    @GetMapping("/highest-rated")
    public ResponseEntity getHighestRatedArtist(){
        logger.info("get artist with highest rate");
        return ResponseEntity.ok(artistService.highestRatedArtist());
    }


}