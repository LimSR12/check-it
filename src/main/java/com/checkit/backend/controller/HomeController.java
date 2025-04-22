package com.checkit.backend.controller;

import com.checkit.backend.global.response.APIResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    
    @GetMapping("/")
    public APIResponse<?> home(){
        return APIResponse.success("Welcome to Check-It!");
    }
}
