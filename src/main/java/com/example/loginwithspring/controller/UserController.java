package com.example.loginwithspring.controller;

import java.io.IOException;
import java.nio.file.Files;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UserController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    // Simple logout method - no additional imports needed
    @GetMapping("/logout")
    public String handleLogoutRequest() {
        SecurityContextHolder.clearContext(); // Clear security context
        return "redirect:/login?logout";
    }

    @GetMapping("/login")
    public String showLoginForm(org.springframework.ui.Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }
        return "redirect:/";
    }

    // ✅ Add this method to serve images from resources/images/
    @GetMapping("/images/{filename}")
    public ResponseEntity<byte[]> getImage(@PathVariable String filename) throws IOException {
        Resource resource = new ClassPathResource("images/" + filename); // Loads from src/main/resources/images/

        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        byte[] imageBytes = Files.readAllBytes(resource.getFile().toPath());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath())); // Set correct MIME type

        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }
}