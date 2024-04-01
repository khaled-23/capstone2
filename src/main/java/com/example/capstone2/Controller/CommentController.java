package com.example.capstone2.Controller;

import com.example.capstone2.ApiResponse.ApiResponse;
import com.example.capstone2.Model.Comment;
import com.example.capstone2.Service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/add")
    public ResponseEntity addComment(@RequestBody @Valid Comment comment, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        commentService.addComment(comment);
        return ResponseEntity.ok(new ApiResponse("comment added"));
    }

    @GetMapping("/comments")
    public ResponseEntity getAllComments(){
        return ResponseEntity.ok(commentService.getAllComment());
    }


    @PutMapping("/update/{commentId}")
    public ResponseEntity updateComment(@PathVariable Integer commentId, @RequestBody @Valid Comment comment, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        commentService.updateComment(commentId,comment);
        return ResponseEntity.ok(new ApiResponse("comment updated"));
    }

    @DeleteMapping("/remove/{commentId}")
    public ResponseEntity removeComment(@PathVariable Integer commentId){
        commentService.removeComment(commentId);
        return ResponseEntity.ok(new ApiResponse("comment removed"));
    }

    @GetMapping("/get-artist-comments/{artistId}")
    public ResponseEntity getAllCommentsByArtistPage(@PathVariable Integer artistId){
        return ResponseEntity.ok(commentService.getAllCommentsByArtistId(artistId));
    }

    @GetMapping("/get-user-comments/{userId}")
    public ResponseEntity getAllCommentsByUserId(@PathVariable Integer userId){
        return ResponseEntity.ok(commentService.getAllCommentByUserId(userId));
    }
}
