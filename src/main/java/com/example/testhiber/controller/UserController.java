package com.example.testhiber.controller;

import com.example.testhiber.dto.UserDto;
import com.example.testhiber.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

//    @GetMapping
//    public String userLst(Model model){
//        model.addAttribute("users", userService.getAll());
//        return "userList";
//    }

    @GetMapping("/new")
    public String newUser(Model model){
        model.addAttribute("user", new UserDto());
        return "user";
    }
    @PostMapping("/new")
    public String saveUser(UserDto dto, Model model){
        if (userService.save(dto)){
            return "redirect:/";///
        }else {
            model.addAttribute("user", dto);
            return "user";
        }
    }
}
