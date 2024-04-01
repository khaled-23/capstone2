package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor
@Entity@NoArgsConstructor
public class ArtistPage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer artistPageId;
    @NotEmpty(message = "description should not be empty")
    @Size(min = 20, max = 200, message = "minimum 20 character, maximum 200 character")
    @Column(columnDefinition = "VARCHAR(200) not null")
    private String description;
//    @NotNull(message = "isVisible should not be null")
//    @AssertFalse(message = "isVisible should be false")
//    @Column(columnDefinition = "BOOLEAN NOT NULL")
//    private Boolean isVisible;
    @NotNull(message = "doesAcceptOrder should not be null")
    @Column(columnDefinition = "BOOLEAN NOT NULL")
    private Boolean doesAcceptOrder;
    @NotNull(message = "artist id should not be null")
    @Column(columnDefinition = "INT NOT NULL UNIQUE")
    private Integer ArtistId;
}
