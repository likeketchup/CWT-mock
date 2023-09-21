package com.example.cwt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@Table(name = "Token")
public class Token {
    @Id
    private String email;
    private String token;
    private String expiration_timestamp;
    public Token() {}
}
