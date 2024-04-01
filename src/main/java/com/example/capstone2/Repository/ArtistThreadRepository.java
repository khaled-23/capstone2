package com.example.capstone2.Repository;

import com.example.capstone2.Model.ArtistThread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistThreadRepository extends JpaRepository<ArtistThread, Integer> {
}
