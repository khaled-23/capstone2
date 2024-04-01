package com.example.capstone2.Repository;

import com.example.capstone2.Model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<Artist,Integer> {

    Artist findArtistByArtistId(Integer artistId);

    Artist findArtistByUsername(String username);

    List<Artist> findArtistsByDoesAcceptOrderTrue();
}
