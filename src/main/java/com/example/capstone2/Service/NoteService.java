package com.example.capstone2.Service;

import com.example.capstone2.ApiResponse.ApiException;
import com.example.capstone2.Model.Note;
import com.example.capstone2.Model.OrderHistory;
import com.example.capstone2.Repository.NoteRepository;
import com.example.capstone2.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;
    private final OrderRepository orderRepository;


    public void addNote(Note note){
        OrderHistory order = orderRepository.findOrderByOrderId(note.getOrderId());
        if(order == null){
            throw new ApiException("order does not exists");
        }
        if(order.getStatus().equals("done")){
            throw new ApiException("can not add to a complete order");
        }
        noteRepository.save(note);
    }

    public List<Note> getAllNote(){
        return noteRepository.findAll();
    }

    public void updateNote(Integer noteId, Note note){
        Note n = noteRepository.findNoteByNoteId(noteId);
        if(n==null){
            throw new ApiException("note does not exists");
        }
        n.setTitle(note.getTitle());
        n.setContent(note.getContent());
        noteRepository.save(n);
    }

    public void deleteNote(Integer noteId){
        Note note = noteRepository.findNoteByNoteId(noteId);
        if(note == null){
            throw new ApiException("note does not exists");
        }
    }


}
