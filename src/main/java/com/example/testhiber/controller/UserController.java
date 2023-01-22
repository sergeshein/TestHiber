package com.example.testhiber.controller;

import com.example.testhiber.dto.UserDto;
import com.example.testhiber.entity.User;
import com.example.testhiber.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;


    @GetMapping
    public String userLst(Model model){
        model.addAttribute("users", userService.getAll());
        return "userList";
    }
    @GetMapping("/new")
    public String newUser(Model model){
        model.addAttribute("user", new UserDto());
        return "user";
    }
    @PostMapping("/new")
    public String saveUser(UserDto dto, Model model){
        if (userService.save(dto)){
            return "redirect:/users";///
        }else {
            model.addAttribute("user", dto);
            return "user";
        }
    }

    @GetMapping("/profile")
    public String profileUser(Model model, Principal principal){
        if(principal == null){
            throw new RuntimeException(" you are not authorize");
        }
        User user = userService.findByName(principal.getName());

        UserDto dto = UserDto.builder()
                .username(user.getName())
                .email(user.getEmail())
                .build();
        model.addAttribute("user", dto);
        return "profile";

    }

    @PostMapping("/profile")
    public String updateProfileUser(UserDto dto, Model model, Principal principal){
        if (principal == null || !Objects.equals(principal.getName(), dto.getUsername())){
            throw new RuntimeException("You are not authorize");
        }
        if (dto.getPassword() != null
        && !dto.getPassword().isEmpty()
        && !Objects.equals(dto.getPassword(), dto.getMatchingPassword())){
            model.addAttribute("user", dto);
            return "profile";
        }
        userService.updateProfile(dto);
        return "redirect:/users/profile";
    }

}
