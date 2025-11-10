package com.portfolio.model;

import java.util.ArrayList;
import java.util.List;

public class Profile {
    private String name;
    private String headline;
    private String summary;
    private String location;
    private String email;
    private String phone;

    private List<Experience> experiences = new ArrayList<>();
    private List<Project> projects = new ArrayList<>();
    private List<Skill> skills = new ArrayList<>();

    public Profile() {
    }

    // getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getHeadline() { return headline; }
    public void setHeadline(String headline) { this.headline = headline; }
    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public List<Experience> getExperiences() { return experiences; }
    public void setExperiences(List<Experience> experiences) { this.experiences = experiences; }
    public List<Project> getProjects() { return projects; }
    public void setProjects(List<Project> projects) { this.projects = projects; }
    public List<Skill> getSkills() { return skills; }
    public void setSkills(List<Skill> skills) { this.skills = skills; }

    public static Profile sample() {
        Profile p = new Profile();
        p.setName("Your Name");
        p.setHeadline("Software Engineer | Backend & Web");
        p.setSummary("Short paragraph about you. Replace this with your LinkedIn summary.");
        p.setLocation("City, Country");
        p.setEmail("you@example.com");
        p.setPhone("+1234567890");

        Experience e = new Experience();
        e.setTitle("Software Engineer");
        e.setCompany("Example Co");
        e.setPeriod("2022 - Present");
        e.setDescription("Worked on backend systems and APIs.");
        p.getExperiences().add(e);

        Project pr = new Project();
        pr.setName("Portfolio Website");
        pr.setDescription("A personal portfolio built with Spring Boot and Thymeleaf.");
        pr.setUrl("https://example.com");
        p.getProjects().add(pr);

        p.getSkills().add(new Skill("Java"));
        p.getSkills().add(new Skill("Spring Boot"));
        p.getSkills().add(new Skill("REST APIs"));

        return p;
    }
}
