package com.portfolio.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ProfileService {

    private com.portfolio.model.Profile profile;

    @org.springframework.context.event.EventListener(org.springframework.boot.context.event.ApplicationReadyEvent.class)
    public void init() {
        // Try to load profile.json from resources, otherwise create a sample profile
        ObjectMapper m = new ObjectMapper();
        try {
            ClassPathResource r = new ClassPathResource("profile.json");
            this.profile = m.readValue(r.getInputStream(), com.portfolio.model.Profile.class);
        } catch (IOException e) {
            this.profile = com.portfolio.model.Profile.sample();
        }
    }

    public com.portfolio.model.Profile getProfile() {
        return profile;
    }

    public void updateProfile(com.portfolio.model.Profile p) {
        this.profile = p;
    }
}
