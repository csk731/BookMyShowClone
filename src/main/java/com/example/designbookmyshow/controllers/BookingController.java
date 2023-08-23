package com.example.designbookmyshow.controllers;

import com.example.designbookmyshow.dtos.BookMovieRequestDto;
import com.example.designbookmyshow.dtos.BookMovieResponseDto;
import com.example.designbookmyshow.dtos.ResponseStatus;
import com.example.designbookmyshow.models.Booking;
import com.example.designbookmyshow.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {
    private BookingService bookingService;
    @Autowired
    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }
    public BookMovieResponseDto bookMovie(BookMovieRequestDto request){
        BookMovieResponseDto response = new BookMovieResponseDto();
        try {
            Booking booking = bookingService.bookMovie(
                                request.getUserId(),
                                request.getShowSeatIds(),
                                request.getShowId());
            response.setResponseStatus(ResponseStatus.SUCCESS);
            response.setBookingId(booking.getId());
            response.setAmount(booking.getAmount());
        }
        catch(Exception e){
            response.setResponseStatus(ResponseStatus.FAILURE);
        }
        return response;
    }
}
