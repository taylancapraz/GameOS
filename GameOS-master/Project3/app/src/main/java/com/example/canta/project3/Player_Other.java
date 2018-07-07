package com.example.canta.project3;


public class Player_Other {

    private static final Player_Other ourInstance = new Player_Other();

    static Player_Other getInstance() {
        return ourInstance;
    }

    private Player_Other() {
    }

    private static String name = " ";
    private static String surname = " ";

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        Player_Other.email = email;
    }

    private static String email = " ";

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        Player_Other.id = id;
    }

    private  static String id = "";
    private static String city = "";
    private static String playerName = "Enter Your Name";

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Player_Other.name = name;
    }

    public static String getSurname() {
        return surname;
    }

    public static void setSurname(String surname) {
        Player_Other.surname = surname;
    }

    public static String getCity() {
        return city;
    }

    public static void setCity(String city) {
        Player_Other.city = city;
    }

    public static String getPlayerName() {
        return playerName;
    }

    public static void setPlayerName(String playerName) {
        Player_Other.playerName = playerName;
    }


}
