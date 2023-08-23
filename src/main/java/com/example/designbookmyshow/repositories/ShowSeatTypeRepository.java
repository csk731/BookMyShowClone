package com.example.designbookmyshow.repositories;

import com.example.designbookmyshow.models.ShowSeatType;
import com.example.designbookmyshow.models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType, Long> {
    List<ShowSeatType> findAllByShow(Show show);
}
