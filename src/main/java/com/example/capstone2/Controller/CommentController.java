package com.example.capstone2.Controller;

import com.example.capstone2.ApiResponse.ApiResponse;
import com.example.capstone2.Model.Comment;
import com.example.capstone2.Service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    Logger logger = LoggerFactory.getLogger(CommentController.class);
    @PostMapping("/add")
    public ResponseEntity addComment(@RequestBody @Valid Comment comment){
        commentService.addComment(comment);
        logger.info("comment added");
        return ResponseEntity.ok(new ApiResponse("comment added"));
    }

    @GetMapping("/comments")
    public ResponseEntity getAllComments(){
        logger.info("all comments requested");
        return ResponseEntity.ok(commentService.getAllComment());
    }


    @PutMapping("/update/{commentId}")
    public ResponseEntity updateComment(@PathVariable Integer commentId, @RequestBody @Valid Comment comment){
        commentService.updateComment(commentId,comment);
        logger.info("comment updated");
        return ResponseEntity.ok(new ApiResponse("comment updated"));
    }

    @DeleteMapping("/remove/{commentId}")
    public ResponseEntity removeComment(@PathVariable Integer commentId){
        commentService.removeComment(commentId);
        logger.info("comment removed");
        return ResponseEntity.ok(new ApiResponse("comment removed"));
    }

    @GetMapping("/get-artist-comments/{artistId}")
    public ResponseEntity getAllCommentsByArtistPage(@PathVariable Integer artistId){
        logger.info("comments by artist id requested");// display users comment on artist page
        return ResponseEntity.ok(commentService.getAllCommentsByArtistId(artistId));
    }

    @GetMapping("/get-user-comments/{userId}")
    public ResponseEntity getAllCommentsByUserId(@PathVariable Integer userId){
        logger.info("comments by user id requested");//user can view all their comments,
        return ResponseEntity.ok(commentService.getAllCommentByUserId(userId));
    }
}
