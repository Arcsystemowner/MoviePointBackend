package com.moviepoint.service;

import com.moviepoint.dto.BookingRequest;
import com.moviepoint.entity.Booking;
import com.moviepoint.entity.Show;
import com.moviepoint.entity.User;
import com.moviepoint.repository.BookingRepository;
import com.moviepoint.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import com.moviepoint.entity.BookingStatus;

@Service
public class BookingService {
  @Autowired
  private BookingRepository bookingRepository;

  @Autowired
  private ShowRepository showRepository;

  @Transactional
  public Booking createBooking(BookingRequest request, User user) {
    Show show = showRepository.findById(request.getShowId())
        .orElseThrow(() -> new RuntimeException("Show not found"));

    // Check if show time hasn't passed
    if (show.getShowTime().isBefore(LocalDateTime.now())) {
      throw new RuntimeException("Show has already started");
    }

    Booking booking = new Booking();
    booking.setUser(user);
    booking.setShow(show);
    booking.setBookingTime(LocalDateTime.now());
    booking.setStatus(BookingStatus.CONFIRMED);
    booking.setSeatNumbers(String.join(",", request.getSeatNumbers()));

    // Calculate total amount based on number of seats and show's base price
    double totalAmount = show.getBasePrice() * request.getSeatNumbers().size();
    booking.setAmount(totalAmount);

    return bookingRepository.save(booking);
  }

  public List<Booking> getUserBookings(User user) {
    return bookingRepository.findByUser(user);
  }

  public Booking getBookingById(Long id) {
    return bookingRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Booking not found"));
  }

  @Transactional
  public Booking cancelBooking(Long id) {
    Booking booking = getBookingById(id);

    // Check if show hasn't started yet
    if (booking.getShow().getShowTime().isBefore(LocalDateTime.now())) {
      throw new RuntimeException("Cannot cancel booking after show has started");
    }

    booking.setStatus(BookingStatus.CANCELLED);
    return bookingRepository.save(booking);
  }
}