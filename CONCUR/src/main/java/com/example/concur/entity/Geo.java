package com.example.concur.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Embeddable
public class Geo {
    private String lat;
    private String lng;

    public Geo() {}
}
