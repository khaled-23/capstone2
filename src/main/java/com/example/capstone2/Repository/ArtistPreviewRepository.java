package com.example.capstone2.Repository;

import com.example.capstone2.Model.ArtistPreview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistPreviewRepository extends JpaRepository<ArtistPreview,Integer> {
    ArtistPreview findArtistPreviewByPreviewId(Integer previewId);

    List<ArtistPreview> findArtistPreviewsByArtistId(Integer artistId);
}
