package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor
@Entity@NoArgsConstructor
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer noteId;
    @NotEmpty(message = "title should not be empty")
    @Size(max = 50,message = "note title should not exceed 50 characters")
    @Column(columnDefinition = "VARCHAR(50) NOT NULL DEFAULT 'no title'")
    private String title;
    @NotEmpty(message = "content should not be empty")
    @Size(max = 200, message = "max characters per note is 200")
    @Column(columnDefinition = "VARCHAR(200) NOT NULL")
    private String content;
    @NotNull(message = "order id should not be empty")
    private Integer orderId;
}
