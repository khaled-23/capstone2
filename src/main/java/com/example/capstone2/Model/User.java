package com.example.capstone2.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor
@Entity@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @NotEmpty(message = "name should not be empty")
    @Pattern(regexp = "^[A-Za-z]+$")
    @Column(columnDefinition = "VARCHAR(20) NOT NULL")
    private String name;
    @NotEmpty(message = "username should not be empty")
    @Size(min = 4, max = 15, message = "username should be between 4 and 15 characters")
    @Column(columnDefinition = "VARCHAR(15) NOT NULL UNIQUE")
    private String username;
    @NotEmpty(message = "password should not be empty")
    @Size(min = 8, max = 15, message = "password should be at least 8 characters max 15 characters")
//    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$\n")
    @Column(columnDefinition = "VARCHAR(15) NOT NULL")
    private String password;
    @NotEmpty(message = "password should not be empty")
    @Email(message = "not a valid email")
    @Column(columnDefinition = "VARCHAR(20) NOT NULL UNIQUE")
    private String email;
    @NotEmpty(message = "phone number should not be empty")
    @Size(min = 10, max = 10,message = "phone number should be 10 digits")
    @Pattern(regexp = "^[0-9]+$")
    @Column(columnDefinition = "VARCHAR(10) NOT NULL UNIQUE")
    private String phoneNumber;
    @NotEmpty(message = "user role should not be empty")
    @Pattern(regexp = "^(user|admin|blocked)")//note change to customer only
    @Column(columnDefinition = "VARCHAR(8) NOT NULL DEFAULT 'customer'")
    private String role;
}