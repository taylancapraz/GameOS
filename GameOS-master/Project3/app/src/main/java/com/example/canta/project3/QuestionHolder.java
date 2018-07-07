package com.example.canta.project3;

/**
 * Created by a_tur on 2/25/2017.
 */
public class  QuestionHolder {
    private static QuestionHolder ourInstance = new QuestionHolder();

    public static QuestionHolder getInstance() {
        return ourInstance;
    }

    public static int questionList[] = new int[15];

    private QuestionHolder() {
    }

    public static void questionListReset(){

        for (int i = 0; i < questionList.length; i++) {
            questionList[i] = 0;
        }
    }

    public static void questionSetter(int index, int statue){
        questionList[index] = statue;
    }

    public static int[] getQuestionList() {
        return questionList;
    }

    public static int getCurrentQuestionNumberForChallange() {
        return currentQuestionNumberForChallange;
    }

    public static void setCurrentQuestionNumberForChallange(int currentQuestionNumberForChallange) {
        QuestionHolder.currentQuestionNumberForChallange = currentQuestionNumberForChallange;
    }

    public static int currentQuestionNumberForChallange;


    public static String getCategoryforChallange() {
        return categoryforChallange;
    }

    public static void setCategoryforChallange(String categoryforChallange) {
        QuestionHolder.categoryforChallange = categoryforChallange;
    }

    public static String categoryforChallange;
}
