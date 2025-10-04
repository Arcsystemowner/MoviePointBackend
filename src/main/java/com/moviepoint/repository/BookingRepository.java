package com.moviepoint.repository;

import com.moviepoint.entity.Booking;
import com.moviepoint.entity.User;
import com.moviepoint.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser(User user);

    List<Booking> findByShow(Show show);

    List<Booking> findByUserAndStatus(User user, String status);
}