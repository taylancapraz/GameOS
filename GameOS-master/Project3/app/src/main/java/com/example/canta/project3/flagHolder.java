package com.example.canta.project3;

/**
 * Created by canta on 3/31/2017.
 */
public class flagHolder {
    private static flagHolder ourInstance = new flagHolder();

    public static flagHolder getInstance() {
        return ourInstance;
    }

    private flagHolder() {
    }
    public int getFlag(int k){
        /*
        if (k == 1){
            return R.drawable.flag1;
        } else if (k == 2){
            return R.drawable.flag2;
        } else if (k == 3){
            return R.drawable.flag3;
        } else if (k == 4){
            return R.drawable.flag4;
        } else if (k == 5){
            return R.drawable.flag5;
        } else if (k == 6){
            return R.drawable.flag6;
        } else if (k == 7){
            return R.drawable.flag7;
        } else if (k == 8){
            return R.drawable.flag8;
        } else if (k == 9){
            return R.drawable.flag9;
        } else if (k == 10){
            return R.drawable.flag10;
        } else if (k == 11){
            return R.drawable.flag11;
        } else if (k == 12){
            return R.drawable.flag12;
        } else if (k == 13){
            return R.drawable.flag13;
        } else if (k == 14){
            return R.drawable.flag14;
        } else if (k == 15){
            return R.drawable.flag15;
        } else if (k == 16){
            return R.drawable.flag16;
        } else if (k == 17){
            return R.drawable.flag17;
        } else if (k == 18){
            return R.drawable.flag18;
        } else if (k == 19){
            return R.drawable.flag19;
        } else if (k == 20){
            return R.drawable.flag20;
        } else if (k == 21){
            return R.drawable.flag21;
        } else if (k == 22){
            return R.drawable.flag22;
        } else if (k == 23){
            return R.drawable.flag23;
        } else if (k == 24){
            return R.drawable.flag24;
        } else if (k == 25){
            return R.drawable.flag25;
        } else if (k == 26){
            return R.drawable.flag26;
        } else if (k == 27){
            return R.drawable.flag27;
        } else if (k == 28){
            return R.drawable.flag28;
        } else if (k == 29){
            return R.drawable.flag29;
        } else if (k == 30){
            return R.drawable.flag30;
        } else if (k == 31){
            return R.drawable.flag31;
        } else if (k == 32){
            return R.drawable.flag32;
        } else if (k == 33){
            return R.drawable.flag33;
        } else if (k == 34){
            return R.drawable.flag34;
        } else if (k == 35){
            return R.drawable.flag35;
        } else if (k == 36){
            return R.drawable.flag36;
        } else if (k == 37){
            return R.drawable.flag37;
        } else if (k == 38){
            return R.drawable.flag38;
        } else if (k == 39){
            return R.drawable.flag39;
        } else if (k == 40){
            return R.drawable.flag40;
        } else if (k == 41){
            return R.drawable.flag41;
        } else if (k == 42){
            return R.drawable.flag42;
        } else if (k == 43){
            return R.drawable.flag43;
        } else if (k == 44){
            return R.drawable.flag44;
        } else if (k == 45){
            return R.drawable.flag45;
        } else if (k == 46){
            return R.drawable.flag46;
        } else if (k == 47){
            return R.drawable.flag47;
        } else if (k == 48){
            return R.drawable.flag48;
        } else if (k == 49){
            return R.drawable.flag49;
        } else if (k == 50){
            return R.drawable.flag50;
        } else if (k == 51){
            return R.drawable.flag51;
        } else if (k == 52){
            return R.drawable.flag52;
        } else if (k == 53){
            return R.drawable.flag53;
        } else if (k == 54){
            return R.drawable.flag54;
        } else if (k == 55){
            return R.drawable.flag55;
        } else if (k == 56){
            return R.drawable.flag56;
        } else if (k == 57){
            return R.drawable.flag57;
        } else if (k == 58){
            return R.drawable.flag58;
        } else if (k == 59){
            return R.drawable.flag59;
        } else if (k == 60){
            return R.drawable.flag60;
        } else if (k == 61){
            return R.drawable.flag61;
        } else if (k == 62){
            return R.drawable.flag62;
        } else if (k == 63){
            return R.drawable.flag63;
        } else if (k == 64){
            return R.drawable.flag64;
        } else if (k == 65){
            return R.drawable.flag65;
        } else if (k == 66){
            return R.drawable.flag66;
        } else if (k == 67){
            return R.drawable.flag67;
        } else if (k == 68){
            return R.drawable.flag68;
        } else if (k == 69){
            return R.drawable.flag69;
        } else if (k == 70){
            return R.drawable.flag70;
        } else if (k == 71){
            return R.drawable.flag71;
        } else if (k == 72){
            return R.drawable.flag72;
        } else if (k == 73){
            return R.drawable.flag73;
        } else if (k == 74){
            return R.drawable.flag74;
        } else if (k == 75){
            return R.drawable.flag75;
        } else if (k == 76){
            return R.drawable.flag76;
        } else if (k == 77){
            return R.drawable.flag77;
        } else if (k == 78){
            return R.drawable.flag78;
        } else if (k == 79){
            return R.drawable.flag79;
        } else if (k == 80){
            return R.drawable.flag80;
        } else if (k == 81){
            return R.drawable.flag81;
        } else if (k == 82){
            return R.drawable.flag82;
        } else if (k == 83){
            return R.drawable.flag83;
        } else if (k == 84){
            return R.drawable.flag84;
        } else if (k == 85){
            return R.drawable.flag85;
        } else if (k == 86){
            return R.drawable.flag86;
        } else if (k == 87){
            return R.drawable.flag87;
        } else if (k == 88){
            return R.drawable.flag88;
        } else if (k == 89){
            return R.drawable.flag89;
        } else if (k == 90){
            return R.drawable.flag90;
        } else if (k == 91){
            return R.drawable.flag91;
        } else if (k == 92){
            return R.drawable.flag92;
        } else if (k == 93){
            return R.drawable.flag93;
        } else if (k == 94){
            return R.drawable.flag94;
        } else if (k == 95){
            return R.drawable.flag95;
        } else if (k == 96){
            return R.drawable.flag96;
        } else if (k == 97){
            return R.drawable.flag97;
        } else if (k == 98){
            return R.drawable.flag98;
        } else if (k == 99){
            return R.drawable.flag99;
        } else if (k == 100){
            return R.drawable.flag100;
        } else if (k == 101){
            return R.drawable.flag101;
        } else if (k == 102){
            return R.drawable.flag102;
        } else if (k == 103){
            return R.drawable.flag103;
        } else if (k == 104){
            return R.drawable.flag104;
        } else if (k == 105){
            return R.drawable.flag105;
        } else if (k == 106){
            return R.drawable.flag106;
        } else if (k == 107){
            return R.drawable.flag107;
        } else if (k == 108){
            return R.drawable.flag108;
        } else if (k == 109){
            return R.drawable.flag109;
        } else if (k == 110){
            return R.drawable.flag110;
        } else if (k == 111){
            return R.drawable.flag111;
        } else if (k == 112){
            return R.drawable.flag112;
        } else if (k == 113){
            return R.drawable.flag113;
        } else if (k == 114){
            return R.drawable.flag114;
        } else if (k == 115){
            return R.drawable.flag115;
        } else if (k == 116){
            return R.drawable.flag116;
        } else if (k == 117){
            return R.drawable.flag117;
        } else if (k == 118){
            return R.drawable.flag118;
        } else if (k == 119){
            return R.drawable.flag119;
        } else if (k == 120){
            return R.drawable.flag120;
        } else if (k == 121){
            return R.drawable.flag121;
        } else if (k == 122){
            return R.drawable.flag122;
        } else if (k == 123){
            return R.drawable.flag123;
        } else if (k == 124){
            return R.drawable.flag124;
        } else if (k == 125){
            return R.drawable.flag125;
        } else if (k == 126){
            return R.drawable.flag126;
        } else if (k == 127){
            return R.drawable.flag127;
        } else if (k == 128){
            return R.drawable.flag128;
        } else if (k == 129){
            return R.drawable.flag129;
        } else if (k == 130){
            return R.drawable.flag130;
        } else if (k == 131){
            return R.drawable.flag131;
        } else if (k == 132){
            return R.drawable.flag132;
        } else if (k == 133){
            return R.drawable.flag133;
        } else if (k == 134){
            return R.drawable.flag134;
        } else if (k == 135){
            return R.drawable.flag135;
        } else if (k == 136){
            return R.drawable.flag136;
        } else if (k == 137){
            return R.drawable.flag137;
        } else if (k == 138){
            return R.drawable.flag138;
        } else if (k == 139){
            return R.drawable.flag139;
        } else if (k == 140){
            return R.drawable.flag140;
        } else if (k == 141){
            return R.drawable.flag141;
        } else if (k == 142){
            return R.drawable.flag142;
        } else if (k == 143){
            return R.drawable.flag143;
        } else if (k == 144){
            return R.drawable.flag144;
        } else if (k == 145){
            return R.drawable.flag145;
        } else if (k == 146){
            return R.drawable.flag146;
        } else if (k == 147){
            return R.drawable.flag147;
        } else if (k == 148){
            return R.drawable.flag148;
        } else if (k == 149){
            return R.drawable.flag149;
        } else if (k == 150){
            return R.drawable.flag150;
        } else if (k == 151){
            return R.drawable.flag151;
        } else if (k == 152){
            return R.drawable.flag152;
        } else if (k == 153){
            return R.drawable.flag153;
        } else if (k == 154){
            return R.drawable.flag154;
        } else if (k == 155){
            return R.drawable.flag155;
        } else if (k == 156){
            return R.drawable.flag156;
        } else if (k == 157){
            return R.drawable.flag157;
        } else if (k == 158){
            return R.drawable.flag158;
        } else if (k == 159){
            return R.drawable.flag159;
        } else if (k == 160){
            return R.drawable.flag160;
        } else if (k == 161){
            return R.drawable.flag161;
        } else if (k == 162){
            return R.drawable.flag162;
        } else if (k == 163){
            return R.drawable.flag163;
        } else if (k == 164){
            return R.drawable.flag164;
        } else if (k == 165){
            return R.drawable.flag165;
        } else if (k == 166){
            return R.drawable.flag166;
        } else if (k == 167){
            return R.drawable.flag167;
        } else if (k == 168){
            return R.drawable.flag168;
        } else if (k == 169){
            return R.drawable.flag169;
        } else if (k == 170){
            return R.drawable.flag170;
        } else if (k == 171){
            return R.drawable.flag171;
        } else if (k == 172){
            return R.drawable.flag172;
        } else if (k == 173){
            return R.drawable.flag173;
        } else if (k == 174){
            return R.drawable.flag174;
        } else if (k == 175){
            return R.drawable.flag175;
        } else if (k == 176){
            return R.drawable.flag176;
        } else if (k == 177){
            return R.drawable.flag177;
        } else if (k == 178){
            return R.drawable.flag178;
        } else if (k == 179){
            return R.drawable.flag179;
        } else if (k == 180){
            return R.drawable.flag180;
        } else if (k == 181){
            return R.drawable.flag181;
        } else if (k == 182){
            return R.drawable.flag182;
        } else if (k == 183){
            return R.drawable.flag183;
        } else if (k == 184){
            return R.drawable.flag184;
        } else if (k == 185){
            return R.drawable.flag185;
        } else if (k == 186){
            return R.drawable.flag186;
        } else if (k == 187){
            return R.drawable.flag187;
        } else if (k == 188){
            return R.drawable.flag188;
        } else if (k == 189){
            return R.drawable.flag189;
        } else if (k == 190){
            return R.drawable.flag190;
        } else if (k == 191){
            return R.drawable.flag191;
        } else if (k == 192){
            return R.drawable.flag192;
        } else if (k == 193){
            return R.drawable.flag193;
        } else if (k == 194){
            return R.drawable.flag194;
        } else if (k == 195){
            return R.drawable.flag195;
        } else if (k == 196){
            return R.drawable.flag196;
        } else if (k == 197){
            return R.drawable.flag197;
        } else if (k == 198){
            return R.drawable.flag198;
        } else if (k == 199){
            return R.drawable.flag199;
        } else if (k == 200){
            return R.drawable.flag200;
        } else if (k == 201){
            return R.drawable.flag201;
        } else if (k == 202){
            return R.drawable.flag202;
        } else if (k == 203){
            return R.drawable.flag203;
        } else if (k == 204){
            return R.drawable.flag204;
        } else if (k == 205){
            return R.drawable.flag205;
        } else if (k == 206){
            return R.drawable.flag206;
        } else if (k == 207){
            return R.drawable.flag207;
        } else if (k == 208){
            return R.drawable.flag208;
        } else if (k == 209){
            return R.drawable.flag209;
        } else if (k == 210){
            return R.drawable.flag210;
        } else if (k == 211){
            return R.drawable.flag211;
        } else if (k == 212){
            return R.drawable.flag212;
        } else if (k == 213){
            return R.drawable.flag213;
        } else if (k == 214){
            return R.drawable.flag214;
        } else if (k == 215){
            return R.drawable.flag215;
        } else if (k == 216){
            return R.drawable.flag216;
        } else if (k == 217){
            return R.drawable.flag217;
        } else if (k == 218){
            return R.drawable.flag218;
        } else if (k == 219){
            return R.drawable.flag219;
        } else if (k == 220){
            return R.drawable.flag220;
        } else if (k == 221){
            return R.drawable.flag221;
        } else if (k == 222){
            return R.drawable.flag222;
        } else if (k == 223){
            return R.drawable.flag223;
        } else {
            return R.drawable.card;
        }*/
        return 1;
    }

}
