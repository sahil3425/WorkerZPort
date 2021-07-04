package com.example.admin.Models;

import android.widget.EditText;

public class InternshipModel {
    String CompanyName, jobtype, Experience, skills,paid,Mail;

    public InternshipModel() {
    }

    public InternshipModel(String companyName, String jobtype, String experience, String skills, String paid, String mail) {
        CompanyName = companyName;
        this.jobtype = jobtype;
        Experience = experience;
        this.skills = skills;
        this.paid = paid;
        Mail = mail;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getJobtype() {
        return jobtype;
    }

    public void setJobtype(String jobtype) {
        this.jobtype = jobtype;
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

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }
}
