package com.example.canta.project3;

/**
 * Created by CanTurgut on 17/05/2017.
 */

public class HoldTheDoor {

    String gametype;
    String username;
    String scoreed2;
    String scoreed1;
    String challanger;
    String category = "math";
    String challangeremail;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public String getChallangeremail() {
        return challangeremail;
    }

    public void setChallangeremail(String challangeremail) {
        this.challangeremail = challangeremail;
    }

    public HoldTheDoor() {

    }

    public String getGametype() {
        return gametype;
    }

    public void setGametype(String gametype) {
        this.gametype = gametype;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getScoreed2() {
        return scoreed2;
    }

    public void setScoreed2(String scoreed2) {
        this.scoreed2 = scoreed2;
    }

    public String getScoreed1() {
        return scoreed1;
    }

    public void setScoreed1(String scoreed1) {
        this.scoreed1 = scoreed1;
    }

    public String getChallanger() {
        return challanger;
    }

    public void setChallanger(String challanger) {
        this.challanger = challanger;
    }
}