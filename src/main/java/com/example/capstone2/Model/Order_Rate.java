package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data@AllArgsConstructor
@Entity@RequiredArgsConstructor
public class Order_Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rateId;
    @Min(value = 1,message = "minimum rate should be 1")
    @Max(value = 5, message = "maximum rate should be 5")
    @Column(columnDefinition = "INT NOT NULL CHECK(rate>=1 or rate<=5)")
    private Integer rate;
    @NotNull(message = "order id should not be null")
    @Column(columnDefinition = "INT NOT NULL UNIQUE")
    private Integer orderId;
}
