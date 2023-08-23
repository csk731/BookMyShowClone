package com.example.designbookmyshow.services;

import com.example.designbookmyshow.models.*;
import com.example.designbookmyshow.repositories.BookingRepository;
import com.example.designbookmyshow.repositories.ShowRepository;
import com.example.designbookmyshow.repositories.ShowSeatRepository;
import com.example.designbookmyshow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private BookingRepository bookingRepository;
    private UserRepository userRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private PriceCalculatorService priceCalculatorService;
    @Autowired
    public BookingService(BookingRepository bookingRepository,
                          UserRepository userRepository,
                          ShowRepository showRepository,
                          ShowSeatRepository showSeatRepository,
                          PriceCalculatorService priceCalculatorService) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.priceCalculatorService = priceCalculatorService;
    }

    public Booking bookMovie(Long userId, List<Long> seatIds, Long showId){
//         ----------- For now we take lock here -----------This will make system slow
//        1. Get user with that userId
//        2. Get show with that showId
//        ----------------TAKE LOCK-------------------
//        3. Get showSeat with seatIds
//        4. Check if all showseats are available
//        5. If no, throw error
//        6. If yes, Change the showseat status to Locked
//        7. Save updated showseats to DB
//        ------------------END LOCK--------------------
//        8. Create corresponding Booking object
//        9. Return that booking object
//        -------------END lock here----------------
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
                //throw exception/error
        }
        User bookedBy = userOptional.get();

        Optional<Show> showOptional = showRepository.findById(showId);
        if(showOptional.isEmpty()){
            //throw error
        }
        Show bookedShow = showOptional.get();

        List<ShowSeat> showSeats = showSeatRepository.findAllById(seatIds);
        for(ShowSeat showSeat : showSeats){
            if(!showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE) ||
                    (showSeat.getShowSeatStatus().equals(ShowSeatStatus.BLOCKED) &&
                            Duration.between(showSeat.getBlockedAt().toInstant(), new Date().toInstant()).toMinutes() > 15))
            {
                    //throw error
            }
        }
        List<ShowSeat> savedShowSeats = new ArrayList<>();
        for(ShowSeat showSeat:showSeats){
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
            showSeat.setBlockedAt(new Date());
            savedShowSeats.add(showSeatRepository.save(showSeat));
        }

        Booking booking = new Booking();
        booking.setUser(bookedBy);
        booking.setShow(bookedShow);
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setBookedAt(new Date());
        booking.setSeats(savedShowSeats);
        booking.setAmount(priceCalculatorService.calculatePrice(savedShowSeats, bookedShow));
        booking.setPayments(new ArrayList<>());
        Booking savedBooking = bookingRepository.save(booking);
        return savedBooking;
    }
}
