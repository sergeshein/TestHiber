package com.example.testhiber.service;

import com.example.testhiber.dto.UserDto;
import com.example.testhiber.entity.Role;
import com.example.testhiber.entity.User;
import com.example.testhiber.repository.UserRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean save(UserDto userDto) {
        if (!Objects.equals(userDto.getPassword(), userDto.getMatchingPassword())) {
            throw new RuntimeException("Password not Equals");
        }
        User user = User.builder()
                .name(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .email(userDto.getEmail())
                .role(Role.CLIENT)
                .build();
        userRepo.save(user);
        return true;
    }

    @Override
    public void save(User user) {
        userRepo.save(user);
    }

    @Override
    public List<UserDto> getAll() {
        return userRepo.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }



    private UserDto toDto(User user) {
        return UserDto.builder()
                .username(user.getName())
                .email(user.getEmail())
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findFirstByName(username);
        if (user == null){
            throw new UsernameNotFoundException("User not found" + username);
        }

        List<GrantedAuthority> roles= new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(user.getRole().name()));

        return new org.springframework.security.core.userdetails.User(
                user.getName(),
                user.getPassword(),
                roles
        );
    }

    @Override
    public User findByName(String name) {
        return userRepo.findFirstByName(name);
    }


    @Override
    @Transactional
    public void updateProfile(UserDto dto) {
        User savedUser = userRepo.findFirstByName(dto.getUsername());
        if (savedUser == null){
            throw new RuntimeException("User notfound by name" + dto.getUsername());
        }

        boolean isChanged = false;
        if (dto.getPassword() != null && dto.getPassword().isEmpty()){
            savedUser.setPassword(passwordEncoder.encode(dto.getPassword()));
            isChanged = true;
        }

        if(!Objects.equals(dto.getEmail(), savedUser.getEmail())){
            savedUser.setEmail(dto.getEmail());
            isChanged = true;
        }
         if(isChanged){
             userRepo.save(savedUser);
         }

    }


}
