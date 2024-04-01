package com.example.capstone2.Controller;

import com.example.capstone2.ApiResponse.ApiResponse;
import com.example.capstone2.Model.Note;
import com.example.capstone2.Service.NoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/note")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;


    @PostMapping("/add")
    public ResponseEntity addNote(@RequestBody @Valid Note note, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        noteService.addNote(note);
        return ResponseEntity.ok(new ApiResponse("note added"));
    }

    @GetMapping("/notes")
    public ResponseEntity getAllNotes(){
        return ResponseEntity.ok(noteService.getAllNote());
    }

    @PutMapping("/update/{artistId}/{noteId}")
    public ResponseEntity updateNote(@PathVariable Integer noteId,@RequestBody @Valid Note note, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        noteService.updateNote(noteId, note);
        return ResponseEntity.ok(new ApiResponse("note updated"));
    }
    @DeleteMapping("/remove/{noteId}")
    public ResponseEntity removeNote(@PathVariable Integer noteId){
        noteService.deleteNote(noteId);
        return ResponseEntity.ok(new ApiResponse("note deleted"));
    }
}


