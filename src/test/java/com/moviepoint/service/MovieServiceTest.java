package com.moviepoint.service;

import com.moviepoint.dto.MovieRequest;
import com.moviepoint.entity.Movie;
import com.moviepoint.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit Tests for MovieService using JUnit 5 and Mockito
 * 
 * JUnit 5 Features Used:
 * 1. @Test - Marks a method as a test case
 * 2. @BeforeEach - Code to run before each test
 * 3. @ExtendWith - Adds Mockito support to JUnit
 * 4. Assertions - Methods to verify expected results
 * 
 * Mockito Features Used:
 * 1. @Mock - Creates a mock object
 * 2. @InjectMocks - Injects mock dependencies
 * 3. when().thenReturn() - Defines mock behavior
 * 4. verify() - Verifies mock interactions
 */
@ExtendWith(MockitoExtension.class) // Enable Mockito support in JUnit 5
public class MovieServiceTest {

    @Mock // Creates a mock MovieRepository
    private MovieRepository movieRepository;

    @InjectMocks // Injects mocked repository into the service
    private MovieService movieService;

    private Movie testMovie;
    private MovieRequest movieRequest;

    @BeforeEach
    void setUp() {
        testMovie = new Movie();
        testMovie.setId(1L);
        testMovie.setTitle("Test Movie");
        testMovie.setDescription("Test Description");
        testMovie.setDuration("120 minutes");
        testMovie.setGenre("Action");
        testMovie.setLanguage("English");
        testMovie.setReleaseDate("2025-09-23");
        testMovie.setPosterUrl("http://example.com/poster.jpg");

        movieRequest = new MovieRequest();
        movieRequest.setTitle("Test Movie");
        movieRequest.setDescription("Test Description");
        movieRequest.setDuration("120 minutes");
        movieRequest.setGenre("Action");
        movieRequest.setLanguage("English");
        movieRequest.setReleaseDate("2025-09-23");
        movieRequest.setPosterUrl("http://example.com/poster.jpg");
    }

    @Test
    void createMovie_WithValidRequest_ShouldReturnCreatedMovie() {
        // Given
        when(movieRepository.save(any(Movie.class))).thenReturn(testMovie);

        // When
        Movie createdMovie = movieService.createMovie(movieRequest);

        // Then
        assertNotNull(createdMovie);
        assertEquals(testMovie.getTitle(), createdMovie.getTitle());
        assertEquals(testMovie.getDescription(), createdMovie.getDescription());
        assertEquals(testMovie.getDuration(), createdMovie.getDuration());
        assertEquals(testMovie.getGenre(), createdMovie.getGenre());
        assertEquals(testMovie.getLanguage(), createdMovie.getLanguage());

        verify(movieRepository, times(1)).save(any(Movie.class));
    }

    @Test
    void getAllMovies_ShouldReturnAllMovies() {
        // Given
        List<Movie> movies = Arrays.asList(testMovie);
        when(movieRepository.findAll()).thenReturn(movies);

        // When
        List<Movie> result = movieService.getAllMovies();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testMovie.getTitle(), result.get(0).getTitle());

        verify(movieRepository, times(1)).findAll();
    }

    @Test
    void getMovieById_WithExistingId_ShouldReturnMovie() {
        // Given
        when(movieRepository.findById(1L)).thenReturn(Optional.of(testMovie));

        // When
        Movie result = movieService.getMovieById(1L);

        // Then
        assertNotNull(result);
        assertEquals(testMovie.getId(), result.getId());
        assertEquals(testMovie.getTitle(), result.getTitle());

        verify(movieRepository, times(1)).findById(1L);
    }

    @Test
    void getMovieById_WithNonExistingId_ShouldThrowException() {
        // Given
        when(movieRepository.findById(999L)).thenReturn(Optional.empty());

        // When/Then
        assertThrows(RuntimeException.class, () -> movieService.getMovieById(999L));
        verify(movieRepository, times(1)).findById(999L);
    }

    @Test
    void getMoviesByLanguage_ShouldReturnMoviesWithSpecificLanguage() {
        // Given
        List<Movie> movies = Arrays.asList(testMovie);
        when(movieRepository.findByLanguage("English")).thenReturn(movies);

        // When
        List<Movie> result = movieService.getMoviesByLanguage("English");

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("English", result.get(0).getLanguage());

        verify(movieRepository, times(1)).findByLanguage("English");
    }

    @Test
    void getMoviesByGenre_ShouldReturnMoviesWithSpecificGenre() {
        // Given
        List<Movie> movies = Arrays.asList(testMovie);
        when(movieRepository.findByGenre("Action")).thenReturn(movies);

        // When
        List<Movie> result = movieService.getMoviesByGenre("Action");

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Action", result.get(0).getGenre());

        verify(movieRepository, times(1)).findByGenre("Action");
    }

    @Test
    void deleteMovie_WithExistingId_ShouldDeleteMovie() {
        // Given
        doNothing().when(movieRepository).deleteById(1L);

        // When
        movieService.deleteMovie(1L);

        // Then
        verify(movieRepository, times(1)).deleteById(1L);
    }

    @Test
    void createMovie_WithNullRequest_ShouldThrowException() {
        // When/Then
        assertThrows(IllegalArgumentException.class, () -> movieService.createMovie(null));
        verify(movieRepository, never()).save(any());
    }

    @Test
    void createMovie_ShouldMapAllFieldsCorrectly() {
        // Given
        when(movieRepository.save(any(Movie.class))).thenAnswer(invocation -> {
            Movie savedMovie = invocation.getArgument(0);
            savedMovie.setId(1L);
            return savedMovie;
        });

        // When
        Movie createdMovie = movieService.createMovie(movieRequest);

        // Then
        assertNotNull(createdMovie);
        assertNotNull(createdMovie.getId());
        assertEquals(movieRequest.getTitle(), createdMovie.getTitle());
        assertEquals(movieRequest.getDescription(), createdMovie.getDescription());
        assertEquals(movieRequest.getDuration(), createdMovie.getDuration());
        assertEquals(movieRequest.getGenre(), createdMovie.getGenre());
        assertEquals(movieRequest.getLanguage(), createdMovie.getLanguage());
        assertEquals(movieRequest.getReleaseDate(), createdMovie.getReleaseDate());
        assertEquals(movieRequest.getPosterUrl(), createdMovie.getPosterUrl());
    }
}