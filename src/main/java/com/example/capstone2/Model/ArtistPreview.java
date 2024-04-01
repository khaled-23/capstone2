package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor
@Entity@NoArgsConstructor
public class ArtistPreview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer previewId;
    @NotEmpty(message = "path should not be empty")
    @Column(columnDefinition = "VARCHAR(50) NOT NULL UNIQUE")
    private String path;
    @NotNull(message = "profile id should not be null")
    @Column(columnDefinition = "INT NOT NULL")
    private Integer artistId;

}
