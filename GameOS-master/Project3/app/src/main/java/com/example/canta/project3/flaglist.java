package com.example.canta.project3;

/**
 * Created by canta on 3/31/2017.
 */
public class flaglist {
    private static flaglist ourInstance = new flaglist();

    public static flaglist getInstance() {
        return ourInstance;
    }

    public static int flag_List[] = new int[12];
    public static int flag_List2[] = new int[20];
    public static int flag_List3[] = new int[30];

    public static int getTarget1() {
        return target1;
    }
    public static int getTarget2() {
        return target2;
    }
    public static int getTarget3() {
        return target3;
    }
    public static int getTarget4() {
        return target4;
    }
    public static int getTarget21() {
        return target21;
    }
    public static int getTarget22() {
        return target22;
    }
    public static int getTarget23() {
        return target23;
    }
    public static int getTarget24() {
        return target24;
    }
    public static int getTarget25() {
        return target25;
    }
    public static int getTarget31() {
        return target31;
    }
    public static int getTarget32() {
        return target32;
    }
    public static int getTarget33() {
        return target33;
    }
    public static int getTarget34() {
        return target34;
    }
    public static int getTarget35() {
        return target35;
    }
    public static int getTarget36() {
        return target36;
    }
    public static int target1;
    public static int target2;
    public static int target3;
    public static int target4;
    public static int target21;
    public static int target22;
    public static int target23;
    public static int target24;
    public static int target25;
    public static int target31;
    public static int target32;
    public static int target33;
    public static int target34;
    public static int target35;
    public static int target36;

    private flaglist() {}

    public static void setTarget1(int target1) {
        flaglist.target1 = target1;
    }

    public static void setTarget2(int target2) {
        flaglist.target2 = target2;
    }

    public static void setTarget3(int target3) {
        flaglist.target3 = target3;
    }

    public static void setTarget4(int target4) {
        flaglist.target4 = target4;
    }

    public static void setTarget21(int target21) {
        flaglist.target21 = target21;
    }

    public static void setTarget22(int target22) {
        flaglist.target22 = target22;
    }

    public static void setTarget23(int target23) {
        flaglist.target23 = target23;
    }

    public static void setTarget24(int target24) {
        flaglist.target24 = target24;
    }

    public static void setTarget25(int target25) {
        flaglist.target25 = target25;
    }

    public static void setTarget31(int target31) {
        flaglist.target31 = target31;
    }

    public static void setTarget32(int target32) {
        flaglist.target32 = target32;
    }

    public static void setTarget33(int target33) {
        flaglist.target33 = target33;
    }

    public static void setTarget34(int target34) {
        flaglist.target34 = target34;
    }

    public static void setTarget35(int target35) {
        flaglist.target35 = target35;
    }

    public static void setTarget36(int target36) {
        flaglist.target36 = target36;
    }

    public static int[] getQuestionList() {
        boolean control;
        flag_List[0] = 0;
        flag_List[1] = 1;
        for (int i1 = 0; i1 < 12; i1++) {
            control = true;
            flag_List[i1] = (int) (Math.random() * 220 + 1);
            for (int i2 = 0; i2 < i1; i2++) {
                if (flag_List[i2] == flag_List[i1]) {
                    control = false;
                }
            }
            if (control == false) {
                i1 = i1 - 1;
            }
        }

        target1 = flag_List[0];
        target2 = flag_List[1];

        target3 = flag_List[2];
        target4 = flag_List[3];

        return flag_List;

    }


    public static int[] getQuestionList2() {
        boolean control;
        flag_List2[0] = 0;
        flag_List2[1] = 1;
        for (int i1 = 0; i1 < 20; i1++) {
            control = true;
            flag_List2[i1] = (int) (Math.random() * 220 + 1);
            for (int i2 = 0; i2 < i1; i2++) {
                if (flag_List2[i2] == flag_List2[i1]) {
                    control = false;
                }
            }
            if (control == false) {
                i1 = i1 - 1;
            }
        }

        target21 = flag_List2[0];
        target22 = flag_List2[1];
        target23 = flag_List2[2];
        target24 = flag_List2[3];
        target25 = flag_List2[4];

        return flag_List2;
    }


    public static int[] getQuestionList3() {
        boolean control;
        flag_List3[0] = 0;
        flag_List3[1] = 1;
        for (int i1 = 0; i1 < 30; i1++) {
            control = true;
            flag_List3[i1] = (int) (Math.random() * 220 + 1);
            for (int i2 = 0; i2 < i1; i2++) {
                if (flag_List3[i2] == flag_List3[i1]) {
                    control = false;
                }
            }
            if (control == false) {
                i1 = i1 - 1;
            }
        }
        target31 = flag_List3[0];
        target32 = flag_List3[1];
        target33 = flag_List3[2];
        target34 = flag_List3[3];
        target35 = flag_List3[4];
        target36 = flag_List3[5];
        return flag_List3;
    }
}




