package com.example.capstone2.Service;

import com.example.capstone2.ApiResponse.ApiException;
import com.example.capstone2.Model.ArtistThread;
import com.example.capstone2.Model.ThreadReply;
import com.example.capstone2.Repository.ArtistRepository;
import com.example.capstone2.Repository.ArtistThreadRepository;
import com.example.capstone2.Repository.ThreadReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ThreadReplyService {
    private final ThreadReplyRepository threadReplyRepository;
    private final ArtistThreadRepository artistThreadRepository;
    private final ArtistRepository artistRepository;

    public List<ThreadReply> getAllThreadReplies(){
        return threadReplyRepository.findAll();
    }

    public void addThreadReply(ThreadReply threadReply){
        if(!artistRepository.existsById(threadReply.getArtistId())){
            throw new ApiException("artist does not exists");
        }
        if(artistThreadRepository.existsById(threadReply.getThreadId())){
            throw new ApiException("thread does not exists");
        }
        threadReplyRepository.save(threadReply);
    }

    public void updateThreadReply(Integer threadReplayId, ThreadReply threadReply){
        ThreadReply tr = threadReplyRepository.findThreadRepliesByReplyId(threadReplayId);

        if(tr == null){
            throw new ApiException("thread replay does not exists");
        }
        tr.setContent(threadReply.getContent());
        threadReplyRepository.save(tr);
    }

    public void removeThreadReply(Integer threadReplayId){
        if(threadReplyRepository.existsById(threadReplayId)){
            throw new ApiException("thread replay does not exists");
        }
        threadReplyRepository.deleteById(threadReplayId);
    }

    public List<ThreadReply> getAllRepliesByThreadId(Integer threadId){
        if(!artistThreadRepository.existsById(threadId)){
            throw new ApiException("thread does not exists");
        }
        return threadReplyRepository.findThreadRepliesByThreadId(threadId);
    }
}
