package com.geek.firstaid.utilities;


public class DoctorInfoBean {


    private String name;
    private String city;
    private String degree;
    private String number;
    private String specialization;
    private String experience;

    public DoctorInfoBean(String name, String city, String degree, String number, String specialization, String experience) {
        this.name = name;
        this.city = city;
        this.degree = degree;
        this.number = number;
        this.specialization = specialization;
        this.experience = experience;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
