package com.example.testhiber.service;


import com.example.testhiber.dto.UserDto;
import com.example.testhiber.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends UserDetailsService { //security
    boolean save(UserDto userDto);
    List<UserDto> getAll();

    void updateProfile(UserDto dto);
    User findByName(String name);
}
