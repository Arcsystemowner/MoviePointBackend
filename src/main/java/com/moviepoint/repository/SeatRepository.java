package com.moviepoint.repository;

import com.moviepoint.entity.Seat;
import com.moviepoint.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByTheater(Theater theater);

    List<Seat> findByTheaterAndType(Theater theater, String type);
}