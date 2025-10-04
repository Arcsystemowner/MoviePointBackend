package com.moviepoint.service;

import com.moviepoint.dto.ShowRequest;
import com.moviepoint.entity.Show;
import com.moviepoint.entity.Movie;
import com.moviepoint.entity.Theater;
import com.moviepoint.repository.ShowRepository;
import com.moviepoint.repository.MovieRepository;
import com.moviepoint.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShowService {
    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    @Transactional
    public Show createShow(ShowRequest request) {
        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        Theater theater = theaterRepository.findById(request.getTheaterId())
                .orElseThrow(() -> new RuntimeException("Theater not found"));

        Show show = new Show();
        show.setMovie(movie);
        show.setTheater(theater);
        show.setShowTime(request.getShowTime());
        show.setBasePrice(request.getBasePrice());

        // Calculate end time based on movie duration
        LocalDateTime endTime = request.getShowTime().plusMinutes(
                Long.parseLong(movie.getDuration().split(" ")[0]));
        show.setEndTime(endTime);

        return showRepository.save(show);
    }

    public List<Show> getShowsByMovie(Long movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        return showRepository.findByMovie(movie);
    }

    public List<Show> getShowsByTheater(Long theaterId) {
        Theater theater = theaterRepository.findById(theaterId)
                .orElseThrow(() -> new RuntimeException("Theater not found"));
        return showRepository.findByTheater(theater);
    }

    public List<Show> getShowsByDateRange(LocalDateTime start, LocalDateTime end) {
        return showRepository.findByShowTimeBetween(start, end);
    }

    public Show getShowById(Long id) {
        return showRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Show not found"));
    }
}