package com.moviepoint.service;

import com.moviepoint.dto.TheaterRequest;
import com.moviepoint.entity.Theater;
import com.moviepoint.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;

@Service
public class TheaterService {
    @Autowired
    private TheaterRepository theaterRepository;

    @Transactional
    public Theater createTheater(TheaterRequest request) {
        Theater theater = new Theater();
        theater.setName(request.getName());
        theater.setLocation(request.getLocation());
        theater.setCity(request.getCity());
        theater.setTotalSeats(request.getTotalSeats());

        return theaterRepository.save(theater);
    }

    public List<Theater> getAllTheaters() {
        return theaterRepository.findAll();
    }

    public Theater getTheaterById(Long id) {
        return theaterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Theater not found"));
    }

    public List<Theater> getTheatersByCity(String city) {
        return theaterRepository.findByCity(city);
    }
}