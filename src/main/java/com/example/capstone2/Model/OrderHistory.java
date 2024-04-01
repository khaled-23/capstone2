package com.example.capstone2.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data@AllArgsConstructor
@Entity@NoArgsConstructor
public class OrderHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    @Positive(message = "number should be positive")
    @Column(columnDefinition = "INT NOT NULL CHECK(price>0)")
    private Double price;
    @Column(columnDefinition = "VARCHAR(6) NOT NULL DEFAULT 'undone'")
    private String status;
    @Column(columnDefinition = "DATE NOT NULL")
    private LocalDate orderDate;
    @NotNull(message = "artistId should not be null")
    @Column(columnDefinition = "INT NOT NULL")
    private Integer artistId;
    @NotNull(message = "userId should not be null")
    @Column(columnDefinition = "INT NOT NULL")
    private Integer userId;
}
