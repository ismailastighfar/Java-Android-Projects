package com.ismailastighfar.retrofit_demo.model;

public class Team {
    public Integer id;
    public String country;
    public String name;
    public Team(String country, String name) {
        this.country = country;
        this.name = name;
    }
}
