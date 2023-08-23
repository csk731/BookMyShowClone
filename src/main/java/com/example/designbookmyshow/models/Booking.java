package com.example.designbookmyshow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseModel{
    @ManyToOne
    private User user;
    private Date bookedAt;
    private int amount;
    @ManyToOne
    private Show show;
    @ManyToMany
    private List<ShowSeat> seats; // one booking can have multiple seats. one showseat can have multiple bookings because (one user might cancel that showseat and other user might have booked it)
    @OneToMany
    private List<Payment> payments;
    @Enumerated(EnumType.ORDINAL)
    private BookingStatus bookingStatus;
}
