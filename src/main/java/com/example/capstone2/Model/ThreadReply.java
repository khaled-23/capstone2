package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class ThreadReply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer replyId;
    @NotEmpty(message = "reply content should not be empty")
    @Size(min = 5, max = 200, message = "reply content should range from 5 to 200")
    @Column(columnDefinition = "VARCHAR(200) NOT NULL")
    private String content;
    @NotNull(message = "reply date should not be null")
    @Column(columnDefinition = "DATE NOT NULL")
    private LocalDate replyDate;
    @NotNull(message = "artist id should not be null")
    @Column(columnDefinition = "INT NOT NULL")
    private Integer artistId;
    @NotNull(message = "thread id should not be null")
    @Column(columnDefinition = "INT NOT NULL")
    private Integer threadId;
}
