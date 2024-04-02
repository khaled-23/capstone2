package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor
@Entity@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;
    @NotEmpty(message = "content should not be empty")
    @Size(min=5, max = 150, message = "content should range between 5 and 150 max")
    @Column(columnDefinition = "VARCHAR(150) NOT NULL")
    private String content;
    @NotNull(message = "user id should not be empty")
    @Column(columnDefinition = "INT NOT NULL UNIQUE")
    private Integer userId;
    @NotNull(message = "artist id should not be empty")
    @Column(columnDefinition = "INT NOT NULL")
    private Integer artistId;

}
