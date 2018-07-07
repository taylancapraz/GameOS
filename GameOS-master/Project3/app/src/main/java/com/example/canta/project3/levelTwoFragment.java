package com.example.canta.project3;

import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

import static com.example.canta.project3.qqTopicSelectFragment.checkConnection;

public class levelTwoFragment extends Fragment implements View.OnClickListener {
    int num;
    int holder0;
    int holder1;
    int holderPic0;
    int holderPic1;
    int trueans;
    int[] imageViewID = new int[30];

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference().child("android.jpg");

    int[] flaglistNum;
    int[] a = new int[21];
    int[] ac = new int[25];
    Bitmap[] allpictures;
    Bitmap[] targets;
    Dialog dialog;

    public levelTwoFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inf2late the layout f2or this f2ragment
        View layout2 = inflater.inflate(R.layout.fragment_two_level, container, false);

        Player.getInstance().setLife(4);

        imageViewID[0] = R.id.f211;
        imageViewID[1] = R.id.f212;
        imageViewID[2] = R.id.f213;
        imageViewID[3] = R.id.f214;
        imageViewID[4] = R.id.f215;
        imageViewID[5] = R.id.f221;
        imageViewID[6] = R.id.f222;
        imageViewID[7] = R.id.f223;
        imageViewID[8] = R.id.f224;
        imageViewID[9] = R.id.f225;
        imageViewID[10] = R.id.f231;
        imageViewID[11] = R.id.f232;
        imageViewID[12] = R.id.f233;
        imageViewID[13] = R.id.f234;
        imageViewID[14] = R.id.f235;
        imageViewID[15] = R.id.f241;
        imageViewID[16] = R.id.f242;
        imageViewID[17] = R.id.f243;
        imageViewID[18] = R.id.f244;
        imageViewID[19] = R.id.f245;
        imageViewID[20] = R.id.f251;
        imageViewID[21] = R.id.f252;
        imageViewID[22] = R.id.f253;
        imageViewID[23] = R.id.f254;
        imageViewID[24] = R.id.f255;

        imageViewID[25] = R.id.newtarget1;
        imageViewID[26] = R.id.newtarget2;
        imageViewID[27] = R.id.newtarget3;
        imageViewID[28] = R.id.newtarget4;
        imageViewID[29] = R.id.newtarget5;

        for (int i = 0; i < imageViewID.length; i++){
            ImageView myImage = (ImageView) layout2.findViewById(imageViewID[i]);
            myImage.setOnClickListener(this);
        }

        if(challangeHandler.isChallange() && challangeHandler.isChallanger()){
            a = flaglist.getInstance().getQuestionList2();
            for (int i = 0; i < a.length; i++){
                FirebaseDatabase.getInstance().getReference("users").child(challangeHandler.getOthersID()).child("challanges").child(challangeHandler.getMyId()).child("flaglist").child(i + "").setValue(a[i]);
            }
        }else if(challangeHandler.isChallange() && !challangeHandler.isChallanger()){

            for (int i = 0; i < a.length; i++){
                final int i2 = i;
                FirebaseDatabase.getInstance().getReference("users").child(challangeHandler.getMyId()).child("challanges").child(challangeHandler.getOthersID()).child("flaglist").child(i + "").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        try {
                            a[i2] = Integer.parseInt(dataSnapshot.getValue().toString());
                        }catch (Exception e){
                            Log.d("aa", "onDataChange: " + e);
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        }else{
            a = flaglist.getInstance().getQuestionList2();
        }




        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_loader);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();


        allpictures = new Bitmap[25];
        flaglistNum = new int[25];
        targets = new Bitmap[5];

        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            public void run() {
                createImageArray(a, flaglistNum, allpictures, targets);
            }
        }, 1250);


        return layout2;
    }

    public void onStart() {
        super.onStart();
        for (int i = 0; i < ac.length; i++){
            ac[i] = 0;
        }
        num = 0;
        trueans = 0;
        ImageView myim1 = (ImageView) getActivity().findViewById(R.id.h21);
        myim1.setImageResource(R.drawable.kalp);
        ImageView myim2 = (ImageView) getActivity().findViewById(R.id.h22);
        myim2.setImageResource(R.drawable.kalp);
        ImageView myim3 = (ImageView) getActivity().findViewById(R.id.h23);
        myim3.setImageResource(R.drawable.kalp);
        ImageView myim4 = (ImageView) getActivity().findViewById(R.id.h24);
        myim4.setImageResource(R.drawable.kalp);
        trueans = 0;

        final ImageView tar1 = (ImageView) getActivity().findViewById(R.id.newtarget1);
        final ImageView tar2 = (ImageView) getActivity().findViewById(R.id.newtarget2);
        final ImageView tar3 = (ImageView) getActivity().findViewById(R.id.newtarget3);
        final ImageView tar4 = (ImageView) getActivity().findViewById(R.id.newtarget4);
        final ImageView tar5 = (ImageView) getActivity().findViewById(R.id.newtarget5);

        tar1.setImageResource(R.drawable.card);
        tar2.setImageResource(R.drawable.card);
        tar3.setImageResource(R.drawable.card);
        tar4.setImageResource(R.drawable.card);
        tar5.setImageResource(R.drawable.card);

        Player.getInstance().setPlayerScore(0);
    }



    public void runthis(){
        dialog.dismiss();
        for (int i = 0; i < 25; i++) {
            imagesetter(imageViewID[i], i, 1);
        }
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        StopwatchFragment stopwatchFragment = new StopwatchFragment();
        ft.replace(R.id.time_container2, stopwatchFragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            public void run() {

                try {
                    closeAll();
                    imagesettertarget(R.id.newtarget1, 0, 1);
                    imagesettertarget(R.id.newtarget2, 1, 1);
                    imagesettertarget(R.id.newtarget3, 2, 1);
                    imagesettertarget(R.id.newtarget4, 3, 1);
                    imagesettertarget(R.id.newtarget5, 4, 1);
                }catch(Exception e){

                }
            }
        }, 5000);
    }


    public void onResume(){
        super.onResume();

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.f211:
                imagesetter(R.id.f211,0,num);
                if(num == 2){
                    closeAll();
                    num = -1;
                }else hold(num, 1);
                num ++;
                break;
            case R.id.f212:
                imagesetter(R.id.f212,1,num);
                if(num == 2){
                    closeAll();
                    num = -1;
                }else hold(num, 2);
                num ++;
                break;
            case R.id.f213:
                imagesetter(R.id.f213,2,num);
                if(num == 2){
                    closeAll();
                    num = -1;
                }else hold(num, 3);
                num ++;
                break;
            case R.id.f214:
                imagesetter(R.id.f214,3,num);
                if(num == 2){
                    closeAll();
                    num = -1;
                }else hold(num,4);
                num ++;
                break;
            case R.id.f215:
                imagesetter(R.id.f215,4,num);
                if(num == 2){
                    closeAll();
                    num = -1;
                }else hold(num, 5);
                num ++;
                break;
            case R.id.f221:
                imagesetter(R.id.f221,5,num);
                if(num == 2){
                    closeAll();
                    num = -1;
                }else hold(num, 6);
                num ++;
                break;
            case R.id.f222:
                imagesetter(R.id.f222,6,num);
                if(num == 2){
                    closeAll();
                    num = -1;
                }else hold(num, 7);
                num ++;
                break;
            case R.id.f223:
                imagesetter(R.id.f223,7,num);
                if(num == 2){
                    closeAll();
                    num = -1;
                }else hold(num, 8);
                num ++;
                break;
            case R.id.f224:
                imagesetter(R.id.f224,8,num);
                if(num == 2){
                    closeAll();
                    num = -1;
                }else hold(num, 9);
                num ++;
                break;
            case R.id.f225:
                imagesetter(R.id.f225,9,num);
                if(num == 2){
                    closeAll();
                    num = -1;
                }else hold(num, 10);
                num ++;
                break;

            case R.id.f231:
                imagesetter(R.id.f231,10,num);
                if(num == 2){
                    closeAll();
                    num = -1;
                }else hold(num, 11);
                num ++;
                break;
            case R.id.f232:
                imagesetter(R.id.f232,11,num);
                if(num == 2){
                    closeAll();
                    num = -1;
                }else hold(num, 12);
                num ++;
                break;
            case R.id.f233:
                imagesetter(R.id.f233,12,num);
                if(num == 2){
                    closeAll();
                    num = -1;
                }else hold(num, 13);
                num ++;
                break;
            case R.id.f234:
                imagesetter(R.id.f234,13,num);
                if(num == 2){
                    closeAll();
                    num = -1;
                }else hold(num, 14);
                num ++;
                break;
            case R.id.f235:
                imagesetter(R.id.f235,14,num);
                if(num == 2){
                    closeAll();
                    num = -1;
                }else hold(num, 15);
                num ++;
                break;
            case R.id.f241:
                imagesetter(R.id.f241,15,num);
                if(num == 2){
                    closeAll();
                    num = -1;
                }else hold(num, 16);
                num ++;
                break;
            case R.id.f242:
                imagesetter(R.id.f242,16,num);
                if(num == 2){
                    closeAll();
                    num = -1;
                }else hold(num, 17);
                num ++;
                break;
            case R.id.f243:
                imagesetter(R.id.f243,17,num);
                if(num == 2){
                    closeAll();
                    num = -1;
                }else hold(num, 18);
                num ++;
                break;
            case R.id.f244:
                imagesetter(R.id.f244,18,num);
                if(num == 2){
                    closeAll();
                    num = -1;
                }else hold(num, 19);
                num ++;
                break;
            case R.id.f245:
                imagesetter(R.id.f245,19,num);
                if(num == 2){
                    closeAll();
                    num = -1;
                }else hold(num, 20);
                num ++;
                break;
            case R.id.f251:
                imagesetter(R.id.f251,20,num);
                if(num == 2){
                    closeAll();
                    num = -1;
                }else hold(num, 21);
                num ++;
                break;
            case R.id.f252:
                imagesetter(R.id.f252,21,num);
                if(num == 2){
                    closeAll();
                    num = -1;
                }else hold(num, 22);
                num ++;
                break;
            case R.id.f253:
                imagesetter(R.id.f253,22,num);
                if(num == 2){
                    closeAll();
                    num = -1;
                }else hold(num, 23);
                num ++;
                break;
            case R.id.f254:
                imagesetter(R.id.f254,23,num);
                if(num == 2){
                    closeAll();
                    num = -1;
                }else hold(num, 24);
                num ++;
                break;
            case R.id.f255:
                imagesetter(R.id.f255,24,num);
                if(num == 2){
                    closeAll();
                    num = -1;
                }else hold(num, 25);
                num ++;
                break;
        }

    }

    public void hold(int number, int k){

        int ref2 = flaglistNum[k-1];
        number ++;
        if (number == 1){
            holderPic0 = k - 1;
            holder0 = ref2;
        }else if (number == 2){
            holderPic1 = k - 1;
            holder1 = ref2;
            System.out.println("Trial : " + holder0 + " at " + holderPic0 + "and " + holder1 + " at " + holderPic1);
            if ((holder0 == holder1) && (holderPic0 != holderPic1)) {
                updatePoint(true);
                trueans++;
                final ImageView tar1 = (ImageView) getActivity().findViewById(R.id.newtarget1);
                final ImageView tar2 = (ImageView) getActivity().findViewById(R.id.newtarget2);
                final ImageView tar3 = (ImageView) getActivity().findViewById(R.id.newtarget3);
                final ImageView tar4 = (ImageView) getActivity().findViewById(R.id.newtarget4);
                final ImageView tar5 = (ImageView) getActivity().findViewById(R.id.newtarget5);
                int t1 = flaglist.getInstance().getTarget21();
                int t2 = flaglist.getInstance().getTarget22();
                int t3 = flaglist.getInstance().getTarget23();
                int t4 = flaglist.getInstance().getTarget24();
                int t5 = flaglist.getInstance().getTarget25();
                if (t1 == holder1) tar1.setImageResource(R.drawable.trueans);
                else if (t2 == holder1) tar2.setImageResource(R.drawable.trueans);
                else if (t3 == holder1) tar3.setImageResource(R.drawable.trueans);
                else if (t4 == holder1) tar4.setImageResource(R.drawable.trueans);
                else if (t5 == holder1) tar5.setImageResource(R.drawable.trueans);
                else if (t1 == holder0) tar1.setImageResource(R.drawable.trueans);
                else if (t2 == holder0) tar1.setImageResource(R.drawable.trueans);
                else if (t3 == holder0) tar3.setImageResource(R.drawable.trueans);
                else if (t4 == holder0) tar4.setImageResource(R.drawable.trueans);
                else if (t5 == holder0) tar5.setImageResource(R.drawable.trueans);
                Log.d("TAGG", t1 + " " + t2 + " " + t3 + " " + t4 + " " + t5 + " " + holder1 + " " + holder0);



                if (trueans == 5) {
                    Player.getInstance().setLife(4);

                    trueans = 0;
                    firebasePointUpdate(0);
                    MemoryGameSelectionFragment move = new MemoryGameSelectionFragment();
                    android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.fragment_container, move);
                    ft.addToBackStack(null);
                    ft.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    ft.commit();
                }
                holderPic0 = -2;
                holderPic1 = -3;
                holder0 = -4;
                holder1 = -5;
            } else{
                Player.getInstance().setLife(Player.getInstance().getLife()-1);
                if(Player.getInstance().getLife() == 3){
                    ImageView myim = (ImageView) getActivity().findViewById(R.id.h21);
                    myim.setImageResource(R.drawable.ekalp);
                }else if(Player.getInstance().getLife() == 2){
                    ImageView myim = (ImageView) getActivity().findViewById(R.id.h22);
                    myim.setImageResource(R.drawable.ekalp);
                }else if(Player.getInstance().getLife() == 1){
                    ImageView myim = (ImageView) getActivity().findViewById(R.id.h23);
                    myim.setImageResource(R.drawable.ekalp);
                }else if(Player.getInstance().getLife() == 0){
                    ImageView myim = (ImageView) getActivity().findViewById(R.id.h24);
                    myim.setImageResource(R.drawable.ekalp);
                }else{
                    firebasePointUpdate(0);
                    MemoryGameSelectionFragment game1 = new MemoryGameSelectionFragment();
                    android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.fragment_container, game1);
                    ft.addToBackStack(null);
                    ft.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    ft.commit();
                }

            }
        }
    }

    public void imagesetter(final int id, int k, int num) {
        ImageView image1 = (ImageView) getActivity().findViewById(id);
        image1.setImageBitmap(allpictures[k]);
    }

    public void imagesettertarget(final int id, int k, int num) {
        ImageView image1 = (ImageView) getActivity().findViewById(id);
        image1.setImageBitmap(targets[k]);
    }


    public void closeAll(){

        for (int i = 0; i < 25; i++){
            ImageView myview = (ImageView) getActivity().findViewById(imageViewID[i]);
            if (ac[i] == 0){
                myview.setImageResource(R.drawable.card);
            }
        }
    }

    static  int incrementer = 0;
    private int createImageArray(final int a[],final int[] flaglistNum,final Bitmap[] allpictures, final Bitmap[] targets) {

            try {
                if (checkConnection(getActivity().getApplicationContext())) {
                    for (int i = 0; i < 20; i++) {
                        final File localFile = File.createTempFile("flag" + a[i] + ".png", "png");
                        storageRef = storage.getReference().child("flag" + a[i] + ".png");
                        final int finalI = i;
                        storageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                if (finalI < 5) {
                                    targets[finalI] = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                    allpictures[20 + finalI] = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                    flaglistNum[20 + finalI] = a[finalI];
                                }
                                flaglistNum[finalI] = a[finalI];
                                allpictures[finalI] = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                MainActivity.mydb.insertImage(a[finalI] + "", allpictures[finalI]);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                            }
                        }).addOnCompleteListener(new OnCompleteListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<FileDownloadTask.TaskSnapshot> task) {
                                incrementer++;
                                if (incrementer == 20) {
                                    runthis();
                                    incrementer = 0;
                                }
                            }
                        });
                    }
                } else {
                    Cursor cursor = MainActivity.mydb.getImage();

                    if (cursor.getCount() == 0) {
                        Log.d("databaseInsert", "Cursor Null");
                    } else {
                        int cnt = 0;
                        while (cursor.moveToNext()) {
                            if(cnt < 20){
                                byte[] image = cursor.getBlob(2);
                                Log.d("databaseInsert", "::: " + image);
                                Bitmap b = BitmapFactory.decodeByteArray(image, 0, image.length);
                                flaglistNum[cnt] = Integer.parseInt(cursor.getString(1));
                                allpictures[cnt] = b;
                                if (cnt < 5){
                                    flaglistNum[20 + cnt] = Integer.parseInt(cursor.getString(1));
                                    allpictures[20 + cnt] = b;
                                    targets[cnt] = b;
                                    if (cnt == 0){
                                        flaglist.getInstance().setTarget21(Integer.parseInt(cursor.getString(1)));
                                    }else if (cnt == 1){
                                        flaglist.getInstance().setTarget22(Integer.parseInt(cursor.getString(1)));
                                    }else if (cnt == 2){
                                        flaglist.getInstance().setTarget23(Integer.parseInt(cursor.getString(1)));
                                    }else if (cnt == 3){
                                        flaglist.getInstance().setTarget24(Integer.parseInt(cursor.getString(1)));
                                    }else if (cnt == 4){
                                        flaglist.getInstance().setTarget25(Integer.parseInt(cursor.getString(1)));
                                    }
                                }
                                cnt ++;
                                if (cnt == 20){
                                    runthis();
                                    cnt = 0;
                                }
                            }
                        }
                    }
                }

            } catch (IOException e) {}

        return 1;
    }

    public void firebasePointUpdate(final int errorSolver){
        final int[] kk = {errorSolver};
        FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString()).child("info").child("mescore").addValueEventListener(new ValueEventListener() {
            @Override public void onDataChange(DataSnapshot dataSnapshot) {
                if (kk[0] == 0){
                    int usersCurrentScore = Integer.parseInt(dataSnapshot.getValue(String.class));
                    usersCurrentScore += Player.getInstance().getPlayerScore();
                    FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString()).child("info").child("mescore").setValue(usersCurrentScore + "");
                    kk[0]++;
                }
            }
            @Override public void onCancelled(DatabaseError error) {}
        });

        if(challangeHandler.getInstance().isChallange()){

            challangeHandler.getInstance().setIsChallange(false);
            if (challangeHandler.getInstance().isChallanger()){
                FirebaseDatabase.getInstance().getReference("users").child(challangeHandler.getInstance().getMyId()).child("challanges").child(challangeHandler.getInstance().getOthersID()).child("scoreer1").setValue(Player.getInstance().getPlayerScore() + "");
                FirebaseDatabase.getInstance().getReference("users").child(challangeHandler.getInstance().getOthersID()).child("challanges").child(challangeHandler.getInstance().getMyId()).child("scoreer1").setValue(Player.getInstance().getPlayerScore() + "");
            } else{
                FirebaseDatabase.getInstance().getReference("users").child(challangeHandler.getInstance().getMyId()).child("challanges").child(challangeHandler.getInstance().getOthersID()).child("scoreer1").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() != null) {
                            QuestionFragment notifier = new QuestionFragment();
                            if (Integer.parseInt(dataSnapshot.getValue().toString()) > Player.getInstance().getPlayerScore()) {
                                Toast.makeText(getActivity(), "YOU LOST", Toast.LENGTH_SHORT).show();
                                notification.getInstance().sendNotification(challangeHandler.getChallangeremail(),"You win against " + Player.getInstance().getPlayerName());
                            } else if (Integer.parseInt(dataSnapshot.getValue().toString()) < Player.getInstance().getPlayerScore()) {
                                Toast.makeText(getActivity(), "YOU WIN", Toast.LENGTH_SHORT).show();
                                notification.getInstance().sendNotification(challangeHandler.getChallangeremail(),"You lost against " + Player.getInstance().getPlayerName());
                            } else {
                                Toast.makeText(getActivity(), "DRAW", Toast.LENGTH_SHORT).show();
                                notification.getInstance().sendNotification(challangeHandler.getChallangeremail(),"You are draw with " + Player.getInstance().getPlayerName());
                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                challangeHandler.getInstance().setIsChallange(false);
                String myid = challangeHandler.getInstance().getMyId();
                String friendID = challangeHandler.getInstance().getOthersID();
                FirebaseDatabase.getInstance().getReference("users").child(myid).child("challanges").child(friendID).removeValue();
                FirebaseDatabase.getInstance().getReference("users").child(friendID).child("challanges").child(myid).removeValue();

            }
        }
    }

    private void updatePoint(boolean k){
        System.out.println("list members are " + holder0 + "and" + holder1);
        if (holder0 == holder1){
            int alala = Player.getInstance().getPlayerScore();
            if (k){
                alala = alala + 100;
                ac[holderPic0] = 1;
                ac[holderPic1] = 1;
            } else{
                alala = alala - 100;
            }

            Player.getInstance().setPlayerScore(alala);
            TextView textScore = (TextView) getActivity().findViewById(R.id.myscore);
            String scoree = "SCORE " + alala;
            textScore.setText(scoree);

        }
    }

}





























