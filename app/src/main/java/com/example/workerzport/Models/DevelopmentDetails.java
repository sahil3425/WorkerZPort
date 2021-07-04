package com.example.workerzport.Models;

public class DevelopmentDetails {
    String CompanyName, Jobtype, Experience, skills, location, Mail;

    public DevelopmentDetails() {

    }

    public DevelopmentDetails(String companyName, String jobtype, String experience, String skills, String location, String mail) {
        CompanyName = companyName;
        Jobtype = jobtype;
        Experience = experience;
        this.skills = skills;
        this.location = location;
        Mail = mail;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getJobtype() {
        return Jobtype;
    }

    public void setJobtype(String jobtype) {
        Jobtype = jobtype;
    }

    public String getExperience() {
        return Experience;
    }

    public void setExperience(String experience) {
        Experience = experience;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }
}