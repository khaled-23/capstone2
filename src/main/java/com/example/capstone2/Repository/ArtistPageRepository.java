package com.example.capstone2.Repository;

import com.example.capstone2.Model.ArtistPage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistPageRepository extends JpaRepository<ArtistPage,Integer> {

    ArtistPage findArtistPageByArtistPageId(Integer artistPageId);

    List<ArtistPage> findArtistPagesByDoesAcceptOrderTrue();

}
