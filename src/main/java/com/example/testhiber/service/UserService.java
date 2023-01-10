package com.example.testhiber.service;


import com.example.testhiber.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService { //security
    boolean save(UserDto userDto);
}
