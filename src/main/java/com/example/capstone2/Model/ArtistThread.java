package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Entity@NoArgsConstructor
public class ArtistThread {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer threadId;
    @NotEmpty(message = "title should not be empty")
    @Size(min = 10, max = 50,message = "title should range from 10 to 50 characters")
    @Column(columnDefinition = "VARCHAR(50) NOT NULL")
    private String title;
    @NotEmpty(message = "content should not be empty")
    @Size(min = 5, max = 200, message = "content size should range from 5 to 200")
    @Column(columnDefinition = "VARCHAR(200) NOT NULL")
    private String content;
    @NotEmpty(message = "status should not be empty")
    @Pattern(regexp = "^(open|locked)$")
    @Column(columnDefinition = "VARCHAR(6) NOT NULL CHECK(status='open' or status='locked')")
    private String status;
    @NotNull(message = "createDate not null")
    @Column(columnDefinition = "DATE NOT NULL")
    private LocalDate createDate;
    @NotNull(message = "visibility should not null")
    @AssertTrue(message = "visibility by default should be true")
    @Column(columnDefinition = "BOOLEAN NOT NULL DEFAULT TRUE")
    private Boolean visibility;
    @NotNull(message = "artist id should not be empty")
    @Column(columnDefinition = "INT NOT NULL")
    private Integer artistId;
}
