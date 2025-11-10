package com.portfolio.controller;

import com.portfolio.model.Profile;
import com.portfolio.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class PortfolioController {

    private final ProfileService profileService;

    @Autowired
    public PortfolioController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/")
    public String index(Model model) {
        Profile profile = profileService.getProfile();
        model.addAttribute("profile", profile);
        return "index";
    }

    @GetMapping("/api/profile")
    @ResponseBody
    public ResponseEntity<Profile> profile() {
        return ResponseEntity.ok(profileService.getProfile());
    }
}

