package com.example.capstone2.Repository;

import com.example.capstone2.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {
    Comment findCommentByCommentId(Integer commentId);
    List<Comment> findCommentsByArtistId(Integer artistId);

    List<Comment> findCommentsByUserId(Integer userId);
}
