package com.moviepoint.repository;

import com.moviepoint.entity.Show;
import com.moviepoint.entity.Theater;
import com.moviepoint.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface ShowRepository extends JpaRepository<Show, Long> {
    List<Show> findByMovie(Movie movie);

    List<Show> findByTheater(Theater theater);

    List<Show> findByShowTimeBetween(LocalDateTime start, LocalDateTime end);

    List<Show> findByMovieAndTheaterAndShowTimeBetween(
            Movie movie, Theater theater, LocalDateTime start, LocalDateTime end);
}