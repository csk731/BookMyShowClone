package com.example.designbookmyshow.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpResponseDto {
    private ResponseStatus responseStatus;
    private String message;
}
