package com.example.capstone2.Repository;

import com.example.capstone2.Model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note,Integer> {

    Note findNoteByNoteId(Integer noteId);
    List<Note> findNotesByOrderId(Integer orderId);
}
