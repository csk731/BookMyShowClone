package com.example.designbookmyshow.repositories;

import com.example.designbookmyshow.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Override
    Booking save(Booking entity);
}
