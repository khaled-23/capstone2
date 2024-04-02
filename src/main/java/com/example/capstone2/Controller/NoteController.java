package com.example.capstone2.Controller;

import com.example.capstone2.ApiResponse.ApiResponse;
import com.example.capstone2.Model.Note;
import com.example.capstone2.Service.NoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/note")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;


    Logger logger = LoggerFactory.getLogger(NoteController.class);

    @PostMapping("/add")
    public ResponseEntity addNote(@RequestBody @Valid Note note){
        noteService.addNote(note);
        logger.info("note added");
        return ResponseEntity.ok(new ApiResponse("note added"));
    }

    @GetMapping("/notes")
    public ResponseEntity getAllNotes(){
        logger.info("all note requested");
        return ResponseEntity.ok(noteService.getAllNote());
    }

    @PutMapping("/update/{noteId}")
    public ResponseEntity updateNote(@PathVariable Integer noteId,@RequestBody @Valid Note note) {
        noteService.updateNote(noteId, note);
        logger.info("note updated");
        return ResponseEntity.ok(new ApiResponse("note updated"));
    }
    @DeleteMapping("/remove/{noteId}")
    public ResponseEntity removeNote(@PathVariable Integer noteId){
        noteService.deleteNote(noteId);
        logger.info("note removed");
        return ResponseEntity.ok(new ApiResponse("note deleted"));
    }
}


