package com.example.testhiber.controller;

import com.example.testhiber.service.SessionObjectHolder;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
public class MainController {
    private final SessionObjectHolder sessionObjectHolder;

    public MainController(SessionObjectHolder sessionObjectHolder) {
        this.sessionObjectHolder = sessionObjectHolder;
    }

    @RequestMapping({"", "/"})
    public String index(Model model, HttpSession http){
        model.addAttribute("amountClicks", sessionObjectHolder.getAmountClick());
        if (http.getAttribute("myID") == null){
            String uuid = UUID.randomUUID().toString();
            http.setAttribute("myID", uuid);
            System.out.println("Created uuid " + uuid);
        }
        model.addAttribute("uuid", http.getAttribute("myID"));
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model){
        model.addAttribute("loginError", true);
        return "login";
    }
}
