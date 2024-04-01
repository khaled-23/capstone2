package com.example.capstone2.Repository;

import com.example.capstone2.Model.ThreadReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThreadReplyRepository extends JpaRepository<ThreadReply, Integer> {
}
