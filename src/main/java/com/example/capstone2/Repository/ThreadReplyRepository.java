package com.example.capstone2.Repository;

import com.example.capstone2.Model.ThreadReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThreadReplyRepository extends JpaRepository<ThreadReply, Integer> {
    ThreadReply findThreadRepliesByReplyId(Integer threadReplayId);

    List<ThreadReply> findThreadRepliesByThreadId(Integer threadId);
}
