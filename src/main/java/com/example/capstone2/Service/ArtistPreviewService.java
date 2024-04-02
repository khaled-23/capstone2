package com.example.capstone2.Service;

import com.example.capstone2.ApiResponse.ApiException;
import com.example.capstone2.Model.ArtistPreview;
import com.example.capstone2.Repository.ArtistPreviewRepository;
import com.example.capstone2.Repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistPreviewService {
    private final ArtistPreviewRepository artistPreviewRepository;
    private final ArtistRepository artistRepository;
    public void addPreview(ArtistPreview artistPreview){
        boolean doesArtistExists = artistRepository.existsById(artistPreview.getArtistId());
        if(!doesArtistExists){
            throw new ApiException("artists does not exists");
        }
        artistPreviewRepository.save(artistPreview);
    }

    public List<ArtistPreview> getAllPreviews(){
        return artistPreviewRepository.findAll();
    }

    public void updatePreview(Integer previewId, ArtistPreview artistPreview){
        boolean doesArtistExists = artistRepository.existsById(artistPreview.getArtistId());
        if(!doesArtistExists){
            throw new ApiException("artists page does not exists");
        }
        ArtistPreview updatedPreview = artistPreviewRepository.findArtistPreviewByPreviewId(previewId);
        updatedPreview.setPath(artistPreview.getPath());
        artistPreviewRepository.save(updatedPreview);
    }

    public void removePreview(Integer previewId){
        boolean doesPreviewExists = artistPreviewRepository.existsById(previewId);
        if(!doesPreviewExists){
            throw new ApiException("preview does not exists");
        }
        artistPreviewRepository.deleteById(previewId);
    }


    public List<ArtistPreview> getArtistPreviews(Integer artistId){
        boolean doesArtistExists = artistRepository.existsById(artistId);
        if(!doesArtistExists){
            throw new ApiException("artist does not exists");
        }
        List<ArtistPreview> artistPreviews = artistPreviewRepository.findArtistPreviewsByArtistId(artistId);
        if(artistPreviews.isEmpty()){
            throw new ApiException("artist does not have previews");
        }
        return artistPreviews;
    }


}
