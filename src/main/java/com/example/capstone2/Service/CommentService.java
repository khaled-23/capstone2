package com.example.capstone2.Service;

import com.example.capstone2.ApiResponse.ApiException;
import com.example.capstone2.Model.*;
import com.example.capstone2.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ArtistRepository artistRepository;
    private final OrderRepository orderRepository;

    public void addComment(Comment comment){
        User user = userRepository.findUserByUserId(comment.getUserId());
        if(user == null){
            throw new ApiException("user does not exists");
        }
        Artist artist = artistRepository.findArtistByArtistId(comment.getArtistId());
        if(artist==null){
            throw new ApiException("artist does not exists");
        }
        List<OrderHistory> orders = orderRepository.findOrdersByUserId(comment.getUserId());
        for(OrderHistory order:orders){
            if(order.getUserId().equals(comment.getUserId())
                    &&order.getArtistId().equals(comment.getArtistId())
                    &&order.getStatus().equals("done")){
                commentRepository.save(comment);
                return;
            }
        }
        throw new ApiException("user does not have an complete order with the artist");
    }

    public List<Comment> getAllComment(){
        return commentRepository.findAll();
    }

    public void updateComment(Integer commentId, Comment comment){
        Comment c = commentRepository.findCommentByCommentId(commentId);
        if(c==null){
            throw new ApiException("comment does not exists");
        }
        c.setContent(comment.getContent());
        commentRepository.save(c);
    }

    public void removeComment(Integer commentId){
        Comment comment = commentRepository.findCommentByCommentId(commentId);
        if(comment==null){
            throw new ApiException("comment does not exists");
        }
        commentRepository.delete(comment);
    }

//    public void commentToArtist(Integer userId, Comment comment){
//        User user = userRepository.findUserByUserId(userId);
//        if(user == null){
//            throw new ApiException("user does not exists");
//        }
//        ArtistPage artistPage =
//    }

    public List<Comment> getAllCommentsByArtistId(Integer artistId){
        return commentRepository.findCommentsByArtistId(artistId);
    }
    public List<Comment>getAllCommentByUserId(Integer userId){
        return commentRepository.findCommentsByUserId(userId);
    }
}
