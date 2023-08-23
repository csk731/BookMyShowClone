package com.example.designbookmyshow.services;

import com.example.designbookmyshow.exception.APIException;
import com.example.designbookmyshow.models.User;
import com.example.designbookmyshow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;
@Service
public class UserService {
    private UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public User signUp(String email, String password) throws Exception {
//        1. Check if user exists with email
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isPresent()){
            throw new APIException("Email already exists");
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setBookings(new ArrayList<>());
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    public User login(String email, String password) throws Exception {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isEmpty()){
            throw new APIException("User doesn't exists");
        }

        User user = userOptional.get();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if(bCryptPasswordEncoder.matches(password, user.getPassword())){
            return user;
        }
        else{
            //throw exception password doesn't match
            throw new RuntimeException();
        }
    }
}
