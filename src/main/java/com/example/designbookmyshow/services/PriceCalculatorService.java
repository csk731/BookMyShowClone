package com.example.designbookmyshow.services;

import com.example.designbookmyshow.models.Show;
import com.example.designbookmyshow.models.ShowSeat;
import com.example.designbookmyshow.models.ShowSeatType;
import com.example.designbookmyshow.repositories.ShowSeatTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PriceCalculatorService {
    private ShowSeatTypeRepository showSeatTypeRepository;
    @Autowired
    public PriceCalculatorService(ShowSeatTypeRepository showSeatTypeRepository){
        this.showSeatTypeRepository = showSeatTypeRepository;
    }


    public int calculatePrice(List<ShowSeat> seats, Show show){
        List<ShowSeatType> showSeatTypeList = showSeatTypeRepository.findAllByShow(show);
        int amount =0;

        for(ShowSeat seat : seats){
            for(ShowSeatType showSeatType :showSeatTypeList){
                if(seat.getSeat().getSeatType().equals(showSeatType.getSeatType())){
                    amount += showSeatType.getPrice();
                    break;
                }
            }
        }
        return amount;
    }
}
