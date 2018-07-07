package com.example.canta.project3;

/**
 * Created by a_tur on 2/24/2017.
 */
public class Question {
    private static String question;
    private static String wrongAns1;
    private static String wrongAns2;
    private static String wrongAns3;
    private static String trueAns;
    private static int score;
    private static int topic;

    public static String getQuestion() {
        return question;
    }

    public static void setQuestion(String question) {
        Question.question = question;
    }

    public static String getWrongAns1() {
        return wrongAns1;
    }

    public static void setWrongAns1(String wrongAns1) {
        Question.wrongAns1 = wrongAns1;
    }

    public static String getWrongAns2() {
        return wrongAns2;
    }

    public static void setWrongAns2(String wrongAns2) {
        Question.wrongAns2 = wrongAns2;
    }

    public static String getWrongAns3() {
        return wrongAns3;
    }

    public static void setWrongAns3(String wrongAns3) {
        Question.wrongAns3 = wrongAns3;
    }

    public static String getTrueAns() {
        return trueAns;
    }

    public static void setTrueAns(String trueAns) {
        Question.trueAns = trueAns;
    }

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        Question.score = score;
    }

    private static Question questionIns = new Question();

    public static int getTopic() {
        return topic;
    }

    public static void setTopic(int topic) {
        Question.topic = topic;
    }

    public static Question getInstance() {
        return questionIns;
    }

    private Question() {
    }
}
