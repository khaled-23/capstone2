package com.example.capstone2.Service;

import com.example.capstone2.ApiResponse.ApiException;
import com.example.capstone2.Model.ArtistPage;
import com.example.capstone2.Repository.ArtistPageRepository;
import com.example.capstone2.Repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistPageService {
    private final ArtistPageRepository artistPageRepository;
    private final ArtistRepository artistRepository;

    public void addArtistPage(ArtistPage artistPage){
        boolean doesArtistExists = artistRepository.existsById(artistPage.getArtistId());
        if(!doesArtistExists){
            throw new ApiException("artist does not exists");
        }
        artistPageRepository.save(artistPage);
    }

    public List<ArtistPage> getAllArtistPage(){
        return artistPageRepository.findAll();
    }

    public void updateArtistPage(Integer artistPageId, ArtistPage artistPage){
        boolean doesArtistExists = artistRepository.existsById(artistPage.getArtistId());
        if(!doesArtistExists){
            throw new ApiException("artist does not exists");
        }
        ArtistPage aP = artistPageRepository.findArtistPageByArtistPageId(artistPageId);
        if(aP == null){
            throw new ApiException("artist page does not exists");
        }
        aP.setDescription(artistPage.getDescription());
        artistPageRepository.save(aP);
    }

    public void removeArtistPage(Integer artistPageId){
        boolean doesArtistPageExists = artistPageRepository.existsById(artistPageId);
        if(!doesArtistPageExists){
            throw new ApiException("artist page does not exists");
        }
        artistPageRepository.deleteById(artistPageId);
    }
    public List<ArtistPage> getAllPagesWhereArtistAcceptOrder(){
        return artistPageRepository.findArtistPagesByDoesAcceptOrderTrue();
    }
}
