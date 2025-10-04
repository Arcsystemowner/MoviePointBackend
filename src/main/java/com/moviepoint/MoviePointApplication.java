package com.moviepoint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for MoviePoint backend server.
 * This class serves as the entry point for the Spring Boot application.
 * 
 * @author Archit Yadav
 * @version 1.0
 */
@SpringBootApplication
public class MoviePointApplication {

  public static void main(String[] args) {
    SpringApplication.run(MoviePointApplication.class, args);
  }
}