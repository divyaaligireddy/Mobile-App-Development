package com.app.gsu.housecallmd.entity;

import java.io.Serializable;

/**
 * Created by Divya on 11-15-2017.
 */

public class DoctorNurse implements Serializable {

    private String emailId;
    private String name;
    private Profession profession;
    private String specialization;
    private Integer hourlyRate;
    //private List<String> availability;

    public DoctorNurse() {
    }

    public DoctorNurse(String emailId, String name, Profession profession, String specialization, Integer hourlyRate) {
        this.emailId = emailId;
        this.name = name;
        this.profession = profession;
        this.specialization = specialization;
        this.hourlyRate = hourlyRate;
//        this.availability = availability;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Integer getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(Integer hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

/*    public List<String> getAvailability() {
        return availability;
    }

    public void setAvailability(List<String> availability) {
        this.availability = availability;
    }*/

    @Override
    public String toString() {
        return name +
                "\n" + profession.name() +
                " - " + specialization +
                "\n$" + hourlyRate +
                "/hr \n";
    }
}
