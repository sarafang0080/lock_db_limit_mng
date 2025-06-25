package com.example.ratelimit.controller;

import com.example.ratelimit.service.RateLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RateLimitController {

    @Autowired
    private RateLimitService rateLimitService;

    @GetMapping("/access")
    public String access(@RequestParam String userId) {
        boolean allowed = rateLimitService.tryAccess(userId);
        return allowed ? "Access granted" : "Rate limit exceeded";
    }
}