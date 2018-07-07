package com.example.canta.project3;

/**
 * Created by canta on 5/18/2017.
 */

class challangeHandler {
    private static final challangeHandler ourInstance = new challangeHandler();

    static challangeHandler getInstance() {
        return ourInstance;
    }

    private challangeHandler() {
    }

    public static boolean isChallange() {
        return isChallange;
    }

    public static void setIsChallange(boolean isChallange) {
        challangeHandler.isChallange = isChallange;
    }

    public static String getMyId() {
        return myId;
    }

    public static void setMyId(String myId) {
        challangeHandler.myId = myId;
    }

    public static String getOthersID() {
        return othersID;
    }

    public static void setOthersID(String othersID) {
        challangeHandler.othersID = othersID;
    }

    public static boolean isChallanger() {
        return isChallanger;
    }

    public static void setIsChallanger(boolean isChallanger) {
        challangeHandler.isChallanger = isChallanger;
    }

    public static String getChallangeremail() {
        return challangeremail;
    }

    public static void setChallangeremail(String challangeremail) {
        challangeHandler.challangeremail = challangeremail;
    }

    public static String getCategory() {
        return category;
    }

    public static void setCategory(String category) {
        challangeHandler.category = category;
    }

    private static String category = " ";
    private static String challangeremail = " ";
    private static String myId = "";
    private  static String othersID = "";
    private static boolean isChallange = false;
    private static boolean isChallanger = true;
}
