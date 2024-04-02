package com.example.capstone2.Controller;

import com.example.capstone2.ApiResponse.ApiResponse;
import com.example.capstone2.Model.ThreadReply;
import com.example.capstone2.Service.ThreadReplyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/thread-reply")
@RequiredArgsConstructor
public class ThreadReplayController {
    private final ThreadReplyService threadReplyService;

    Logger logger = LoggerFactory.getLogger(ThreadReplayController.class);


    @GetMapping("/replies")
    public ResponseEntity getAllReplies(){
        logger.info("all replies requested");
        return ResponseEntity.ok(threadReplyService.getAllThreadReplies());
    }

    @PostMapping("/add")
    public ResponseEntity addReply(@RequestBody @Valid ThreadReply threadReply){
        threadReplyService.addThreadReply(threadReply);
        logger.info("reply added");
        return ResponseEntity.ok(new ApiResponse("thread reply added"));
    }

    @PutMapping("/updateReply")
    public ResponseEntity updateReply(@PathVariable Integer threadReplyId,@RequestBody @Valid ThreadReply threadReply) {
        threadReplyService.updateThreadReply(threadReplyId, threadReply);
        logger.info("reply updated");
        return  ResponseEntity.ok(new ApiResponse("thread reply updated"));
    }

    @DeleteMapping("/removeReply")
    public ResponseEntity removeReply(@PathVariable Integer threadReplyId){
        threadReplyService.removeThreadReply(threadReplyId);
        logger.info("reply removed");
        return ResponseEntity.ok(new ApiResponse("reply removed"));
    }

    @GetMapping("/thread-replies/{threadId}")
    public ResponseEntity getAllRepliesByThreadId(Integer threadId){
        logger.info("replies for a thread requested");
        return ResponseEntity.ok(threadReplyService.getAllRepliesByThreadId(threadId));
    }
}
