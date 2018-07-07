package com.example.canta.project3;

/**
 * Created by CanTurgut on 10/05/2017.
 */

public class CreatedUser {

    String username;

    String name;
    String surname;
    String city;

    String mescore = "0";
    String qqscore = "0";

    public CreatedUser(String username, String name, String surname, String city) {

        this.username = username;
        this.city = city;
        this.name = name;
        this.surname = surname;

    }
}
