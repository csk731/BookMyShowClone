package com.example.designbookmyshow.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDto {
    @NotEmpty
    @Size(min = 3, message = "Name should have atleast 3 characters")
    private String name;
    @Email
    @NotEmpty
    private String email;
    @NotEmpty
    @Size(min = 5, message = "Password should have atleast 5 characters")
    private String password;
}
