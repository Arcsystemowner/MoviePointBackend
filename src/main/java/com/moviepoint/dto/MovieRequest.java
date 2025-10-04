package com.moviepoint.dto;

import lombok.Data;

@Data
public class MovieRequest {
    private String title;
    private String description;
    private String duration;
    private String genre;
    private String language;
    private String releaseDate;
    private String posterUrl;
}