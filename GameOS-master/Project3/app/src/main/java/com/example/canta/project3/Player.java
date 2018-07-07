package com.example.canta.project3;


import java.util.Arrays;
import java.util.List;

public class Player {
    private static Player ourInstance = new Player();

    public static Player getInstance() {
        return ourInstance;
    }

    private static int playerScore = 0;
    private static String name = "";
    private static String surname = "";

    public static String[] getFriends() {
        return Friends;
    }
    public static String[] allPlayer() {
        return allPlayer;
    }

    private static String[] Friends = new String[100];

    public static String[] getAllPlayer() {
        return allPlayer;
    }

    private static String[] allPlayer = new String[100];

    public static String[] getAllPlayerID() {
        return allPlayerID;
    }

    private static String[] allPlayerID = new String[100];

    public static String[] getAllFriendsID() {
        return allFriendsID;
    }

    private static String[] allFriendsID = new String[100];



    public static void addFriend(String friendname, String friendID){
        int pos = 0;
        int checker = -1;
        while (pos < 100 && Friends[pos] != null){
            pos ++;
        }
        if (pos != 100){
            List valid = Arrays.asList(Friends);
            if (valid.contains(friendname)) {
                // is valid
            } else {
                Friends[pos] = friendname;
                allFriendsID[pos] = friendID;
            }

        }
    }

    public static int getFriendCount(){
        int pos = 0;
        while (pos < 100 && Friends[pos] != null){
            pos ++;
        }
        return  pos;
    }
    public static void addUsertoList(String friendname, String friendID){
        int pos = 0;
        while (pos < 100 && allPlayer[pos] != null){
            pos ++;
        }
        if (pos != 100){
            List valid = Arrays.asList(allPlayer);
            if (valid.contains(friendname)) {
                // is valid
            } else {
                allPlayer[pos] = friendname;
                allPlayerID[pos] = friendID;
            }

        }
    }

    public static int getUserCount(){
        int pos = 0;
        while (pos < 100 && allPlayer[pos] != null){
            pos ++;
        }
        return  pos;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Player.name = name;
    }

    public static String getSurname() {
        return surname;
    }

    public static void setSurname(String surname) {
        Player.surname = surname;
    }

    public static String getCity() {
        return city;
    }

    public static void setCity(String city) {
        Player.city = city;
    }

    private static String city = "";
    private static int life = 4;
    public static int getLife() {
        return life;
    }

    public static void setLife(int life) {
        Player.life = life;
    }


    private static String playerName = "Enter Your Name";
    private static Player player = new Player();

    private Player() {}


    public static String getPlayerName() {
        return playerName;
    }

    public static void setPlayerName(String playerName) {
        Player.playerName = playerName;
    }

    public static int getPlayerScore() {
        return playerScore;
    }

    public static void setPlayerScore(int playerScore) {
        Player.playerScore = playerScore;
    }
}
