package com.example.designbookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Payment extends BaseModel{
    @Enumerated(EnumType.ORDINAL)
    private PaymentStatus status;
    private int amount;
    private String referenceNumber;
    @Enumerated(EnumType.ORDINAL)
    private PaymentProvider provider;
}
