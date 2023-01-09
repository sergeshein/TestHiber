package com.example.testhiber.service;


import com.example.testhiber.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService { //security
    boolean save(UserDto userDto);
}
