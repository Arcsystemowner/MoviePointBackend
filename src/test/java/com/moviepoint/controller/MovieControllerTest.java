package com.moviepoint.controller;

import com.moviepoint.dto.MovieRequest;
import com.moviepoint.entity.Movie;
import com.moviepoint.service.MovieService;
import com.moviepoint.security.JwtTokenProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration Tests for MovieController using Spring's MockMvc
 * 
 * Key Testing Components Used:
 * 
 * 1. @WebMvcTest
 * - Loads only web layer (controllers)
 * - Faster than @SpringBootTest
 * - Perfect for testing REST endpoints
 * 
 * 2. MockMvc
 * - Simulates HTTP requests
 * - Tests endpoints without starting server
 * - Verifies response status, content, headers
 * 
 * 3. @MockBean
 * - Creates Spring-managed mock
 * - Replaces real service with mock
 * - Allows controlling service behavior
 */
@SpringBootTest
@AutoConfigureMockMvc
public class MovieControllerTest {

        @Autowired
        private MockMvc mockMvc; // For simulating HTTP requests

        @MockBean
        private MovieService movieService; // Mock the service layer

        @MockBean
        private JwtTokenProvider jwtTokenProvider; // Mock security dependency

        @MockBean
        private UserDetailsService userDetailsService; // Mock security dependency

        @Autowired
        private ObjectMapper objectMapper; // For JSON conversion

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

        /**
         * Test GET /api/movies endpoint
         * Demonstrates:
         * - Testing GET requests
         * - Verifying JSON response
         * - Checking HTTP status
         */
        @Test
        @WithMockUser
        void getAllMovies_ShouldReturnMoviesList() throws Exception {
                when(movieService.getAllMovies())
                                .thenReturn(Arrays.asList(testMovie));

                mockMvc.perform(get("/api/movies"))
                                .andExpect(status().isOk())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$[0].title").value(testMovie.getTitle()))
                                .andExpect(jsonPath("$[0].genre").value(testMovie.getGenre()));
        }

        @Test
        @WithMockUser(username = "admin", roles = "ADMIN")
        void createMovie_WithValidData_ShouldReturnCreatedMovie() throws Exception {
                when(movieService.createMovie(any(MovieRequest.class)))
                                .thenReturn(testMovie);

                mockMvc.perform(post("/api/movies")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(movieRequest)))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.title").value(testMovie.getTitle()))
                                .andExpect(jsonPath("$.genre").value(testMovie.getGenre()));
        }

        @Test
        @WithMockUser
        void getMovieById_WithValidId_ShouldReturnMovie() throws Exception {
                when(movieService.getMovieById(1L))
                                .thenReturn(testMovie);

                mockMvc.perform(get("/api/movies/1"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.id").value(testMovie.getId()))
                                .andExpect(jsonPath("$.title").value(testMovie.getTitle()));
        }

        @Test
        @WithMockUser
        void getMoviesByLanguage_ShouldReturnMoviesList() throws Exception {
                when(movieService.getMoviesByLanguage("English"))
                                .thenReturn(Arrays.asList(testMovie));

                mockMvc.perform(get("/api/movies/language/English"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$[0].language").value(testMovie.getLanguage()));
        }

        @Test
        @WithMockUser
        void getMoviesByGenre_ShouldReturnMoviesList() throws Exception {
                when(movieService.getMoviesByGenre("Action"))
                                .thenReturn(Arrays.asList(testMovie));

                mockMvc.perform(get("/api/movies/genre/Action"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$[0].genre").value(testMovie.getGenre()));
        }

        @Test
        @WithMockUser(username = "admin", roles = "ADMIN")
        void deleteMovie_WithValidId_ShouldReturnSuccess() throws Exception {
                mockMvc.perform(delete("/api/movies/1"))
                                .andExpect(status().isOk())
                                .andExpect(content().string("Movie deleted successfully"));
        }

        @Test
        @WithMockUser(roles = "USER")
        void createMovie_WithoutAdminRole_ShouldReturnForbidden() throws Exception {
                mockMvc.perform(post("/api/movies")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(movieRequest)))
                                .andExpect(status().isForbidden());
        }

        @Test
        @WithMockUser(roles = "USER")
        void deleteMovie_WithoutAdminRole_ShouldReturnForbidden() throws Exception {
                mockMvc.perform(delete("/api/movies/1"))
                                .andExpect(status().isForbidden());
        }
}