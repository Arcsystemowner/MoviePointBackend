package com.moviepoint.dto;

import lombok.Data;

@Data
public class TheaterRequest {
    private String name;
    private String location;
    private String city;
    private Integer totalSeats;
}