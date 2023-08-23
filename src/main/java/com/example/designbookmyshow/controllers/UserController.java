package com.example.designbookmyshow.controllers;

import com.example.designbookmyshow.dtos.*;
import com.example.designbookmyshow.models.User;
import com.example.designbookmyshow.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookmyshow/user")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }
    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto request){
        SignUpResponseDto response = new SignUpResponseDto();
        try {
            User user = userService.signUp(request.getEmail(), request.getPassword());
            response.setResponseStatus(ResponseStatus.SUCCESS);
            response.setMessage("User registered successfully");
        }catch (Exception e){
            response.setMessage(e.getMessage());
            response.setResponseStatus(ResponseStatus.FAILURE);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/login")
    public LoginResponseDto login(LoginRequestDto request){
        LoginResponseDto response = new LoginResponseDto();
        try {
            User user = userService.login(request.getEmail(), request.getPassword());
            response.setStatus(ResponseStatus.SUCCESS);
            response.setUserId(user.getId());
        }catch(Exception e) {
            response.setStatus(ResponseStatus.FAILURE);
        }
        return response;
    }
}
