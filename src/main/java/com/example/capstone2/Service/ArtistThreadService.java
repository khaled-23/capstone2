package com.example.capstone2.Service;

import com.example.capstone2.ApiResponse.ApiException;
import com.example.capstone2.Model.ArtistThread;
import com.example.capstone2.Model.User;
import com.example.capstone2.Repository.ArtistRepository;
import com.example.capstone2.Repository.ArtistThreadRepository;
import com.example.capstone2.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistThreadService {
    private final ArtistThreadRepository artistThreadRepository;
    private final ArtistRepository artistRepository;
    private final UserRepository userRepository;

    public List<ArtistThread> getAllArtistThreads(){
        return artistThreadRepository.findAll();
    }

    public void addArtistThread(ArtistThread artistThread){
        boolean doesArtistExists = artistRepository.existsById(artistThread.getArtistId());
        if(!doesArtistExists){
            throw new ApiException("arist does not exists");
        }
        artistThread.setCreateDate(LocalDate.now());
        artistThreadRepository.save(artistThread);
    }

    public void updateArtistThread(Integer artistThreadId, ArtistThread artistThread){
        ArtistThread at = artistThreadRepository.findArtistThreadByThreadId(artistThreadId);
        if(at == null){
            throw new ApiException("thread does not exists");
        }
        at.setTitle(artistThread.getTitle());
        at.setContent(artistThread.getContent());
        at.setStatus(artistThread.getStatus());
        at.setIsVisible(artistThread.getIsVisible());
        artistThreadRepository.save(at);
    }

    public void removeArtistThread(Integer artistThreadId){
        boolean doesArtistThreadExists = artistThreadRepository.existsById(artistThreadId);
        if(!doesArtistThreadExists){
            throw new ApiException("thread does not exists");
        }
        artistThreadRepository.deleteById(artistThreadId);
    }

    public List<ArtistThread> searchArtistThreads(String keyword){
        List<ArtistThread> artistThreads = new ArrayList<>();
        for(ArtistThread artistThread:artistThreadRepository.findAll()){
            if(artistThread.getTitle().contains(keyword)
                    &&artistThread.getContent().contains(keyword)
                    && artistThread.getIsVisible()){
                artistThreads.add(artistThread);
            }
        }
        if(artistThreads.isEmpty()){
            throw new ApiException("no threads by given keyword");
        }
        return artistThreads;
    }

    public void pinThread(Integer userId, Integer artistThreadId,Boolean choice){
        User user = userRepository.findUserByUserId(userId);
        if(user == null){
            throw new ApiException("user does not exists");
        }
        if(!user.getRole().equals("admin")){
            throw new ApiException("user is not an admin");
        }
        ArtistThread artistThread = artistThreadRepository.findArtistThreadByThreadId(artistThreadId);
        artistThread.setIsVisible(choice);
        artistThreadRepository.save(artistThread);
    }

    public List<ArtistThread> getAllImportantThread(){
        return artistThreadRepository.findArtistThreadsByIsImportantTrue();
    }
}
