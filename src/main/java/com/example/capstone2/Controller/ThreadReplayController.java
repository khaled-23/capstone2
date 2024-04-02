package com.example.capstone2.Controller;

import com.example.capstone2.ApiResponse.ApiResponse;
import com.example.capstone2.Model.ThreadReply;
import com.example.capstone2.Service.ThreadReplyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/thread-reply")
@RequiredArgsConstructor
public class ThreadReplayController {
    private final ThreadReplyService threadReplyService;



    @GetMapping("/replies")
    public ResponseEntity getAllReplies(){
        return ResponseEntity.ok(threadReplyService.getAllThreadReplies());
    }

    public ResponseEntity addReplay(@RequestBody @Valid ThreadReply threadReply, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        threadReplyService.addThreadReply(threadReply);
        return ResponseEntity.ok(new ApiResponse("thread reply added"));
    }

    public ResponseEntity updateReply(@PathVariable Integer threadReplyId,@RequestBody @Valid ThreadReply threadReply, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        threadReplyService.updateThreadReply(threadReplyId, threadReply);
        return  ResponseEntity.ok(new ApiResponse("thread reply updated"));
    }

    public ResponseEntity removeReply(@PathVariable Integer threadReplyId){
        threadReplyService.removeThreadReply(threadReplyId);
        return ResponseEntity.ok(new ApiResponse("reply removed"));
    }
    public ResponseEntity getAllRepliesByThreadId(Integer threadId){
        return ResponseEntity.ok(threadReplyService.getAllRepliesByThreadId(threadId));
    }
}
