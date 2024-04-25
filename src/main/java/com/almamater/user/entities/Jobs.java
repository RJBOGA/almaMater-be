package com.almamater.user.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Jobs implements Serializable {

    private static final long serialVersionUID = 1L;
    private int jobId;
    private String profile;
    private String desc;
    private int exp;
    private String techs;
    private int studentId;
    private String contact;
    private LocalDate datePosted;

    public Jobs() {
        super();
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public String getTechs() {
        return techs;
    }

    public void setTechs(String techs) {
        this.techs = techs;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public LocalDate getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(LocalDate datePosted) {
        this.datePosted = datePosted;
    }

    @Override
    public String toString() {
        return "Jobs [jobId=" + jobId + ", profile=" + profile + ", desc=" + desc + ", exp=" + exp + ", techs=" + techs
                + ", studentId=" + studentId + ", contact=" + contact + ", datePosted='" + datePosted + "']";
    }

}


