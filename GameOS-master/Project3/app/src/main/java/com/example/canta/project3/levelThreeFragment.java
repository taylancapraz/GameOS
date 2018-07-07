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

public class levelThreeFragment extends Fragment implements View.OnClickListener {
    int num;
    int holder0;
    int holder1;
    int holderPic0;
    int holderPic1;
    int trueans3;
    int[] imageViewID = new int[42];

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference().child("android.jpg");

    int[] flaglistNum;
    int[] a = new int[30];
    int[] ac = new int[42];
    Bitmap[] allpictures;
    Bitmap[] targets;
    Dialog dialog;

    public levelThreeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inf3late the layout f3or this f3ragment
        View layout3 = inflater.inflate(R.layout.fragment_level_three, container, false);

        Player.getInstance().setLife(4);

        imageViewID[0] = R.id.f311;
        imageViewID[1] = R.id.f312;
        imageViewID[2] = R.id.f313;
        imageViewID[3] = R.id.f314;
        imageViewID[4] = R.id.f315;
        imageViewID[5] = R.id.f316;
        imageViewID[6] = R.id.f321;
        imageViewID[7] = R.id.f322;
        imageViewID[8] = R.id.f323;
        imageViewID[9] = R.id.f324;
        imageViewID[10] = R.id.f325;
        imageViewID[11] = R.id.f326;
        imageViewID[12] = R.id.f331;
        imageViewID[13] = R.id.f332;
        imageViewID[14] = R.id.f333;
        imageViewID[15] = R.id.f334;
        imageViewID[16] = R.id.f335;
        imageViewID[17] = R.id.f336;
        imageViewID[18] = R.id.f341;
        imageViewID[19] = R.id.f342;
        imageViewID[20] = R.id.f343;
        imageViewID[21] = R.id.f344;
        imageViewID[22] = R.id.f345;
        imageViewID[23] = R.id.f346;
        imageViewID[24] = R.id.f351;
        imageViewID[25] = R.id.f352;
        imageViewID[26] = R.id.f353;
        imageViewID[27] = R.id.f354;
        imageViewID[28] = R.id.f355;
        imageViewID[29] = R.id.f356;
        imageViewID[30] = R.id.f361;
        imageViewID[31] = R.id.f362;
        imageViewID[32] = R.id.f363;
        imageViewID[33] = R.id.f364;
        imageViewID[34] = R.id.f365;
        imageViewID[35] = R.id.f366;

        Player.getInstance().setLife(4);
        for (int i = 0; i < 36; i++) {
            Log.d("baban", "onCreateView: " + i);
            ImageView myImage = (ImageView) layout3.findViewById(imageViewID[i]);
            myImage.setOnClickListener(this);
        }

        if (challangeHandler.isChallange() && challangeHandler.isChallanger()) {
            a = flaglist.getInstance().getQuestionList3();
            for (int i = 0; i < a.length; i++) {
                FirebaseDatabase.getInstance().getReference("users").child(challangeHandler.getOthersID()).child("challanges").child(challangeHandler.getMyId()).child("flaglist").child(i + "").setValue(a[i]);
            }
        } else if (challangeHandler.isChallange() && !challangeHandler.isChallanger()) {

            for (int i = 0; i < a.length; i++) {
                final int i2 = i;
                FirebaseDatabase.getInstance().getReference("users").child(challangeHandler.getMyId()).child("challanges").child(challangeHandler.getOthersID()).child("flaglist").child(i + "").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        try {
                            a[i2] = Integer.parseInt(dataSnapshot.getValue().toString());
                        } catch (Exception e) {
                            Log.d("aa", "onDataChange: " + e);
                        }
                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        } else {
            a = flaglist.getInstance().getQuestionList3();
        }


        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_loader);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();

        allpictures = new Bitmap[36];
        flaglistNum = new int[36];
        targets = new Bitmap[6];

        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            public void run() {
                createImageArray(a, flaglistNum, allpictures, targets);
            }
        }, 1500);


        return layout3;
    }

    public void onStart() {
        super.onStart();
        num = 0;
        ImageView myim1 = (ImageView) getActivity().findViewById(R.id.h31);
        myim1.setImageResource(R.drawable.kalp);
        ImageView myim2 = (ImageView) getActivity().findViewById(R.id.h32);
        myim2.setImageResource(R.drawable.kalp);
        ImageView myim3 = (ImageView) getActivity().findViewById(R.id.h33);
        myim3.setImageResource(R.drawable.kalp);
        ImageView myim4 = (ImageView) getActivity().findViewById(R.id.h34);
        myim4.setImageResource(R.drawable.kalp);
        trueans3 = 0;
        final int myid3;
        int c[] = flaglist.getInstance().getQuestionList3();

        final ImageView tar1 = (ImageView) getActivity().findViewById(R.id.target1);
        final ImageView tar2 = (ImageView) getActivity().findViewById(R.id.target2);
        final ImageView tar3 = (ImageView) getActivity().findViewById(R.id.target3);
        final ImageView tar4 = (ImageView) getActivity().findViewById(R.id.target8);
        final ImageView tar5 = (ImageView) getActivity().findViewById(R.id.target5);
        final ImageView tar6 = (ImageView) getActivity().findViewById(R.id.target6);
        tar1.setImageResource(R.drawable.card);
        tar2.setImageResource(R.drawable.card);
        tar3.setImageResource(R.drawable.card);
        tar4.setImageResource(R.drawable.card);
        tar5.setImageResource(R.drawable.card);
        tar6.setImageResource(R.drawable.card);

        Player.getInstance().setPlayerScore(0);

    }

    public void onResume() {
        super.onResume();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.f311:
                imagesetter(R.id.f311, 0, num);
                if (num == 2) {
                    closeAll();
                    num = -1;
                } else hold(num, 1);
                num++;
                break;
            case R.id.f312:
                imagesetter(R.id.f312, 1, num);
                if (num == 2) {
                    closeAll();
                    num = -1;
                } else hold(num, 2);
                num++;
                break;
            case R.id.f313:
                imagesetter(R.id.f313, 2, num);
                if (num == 2) {
                    closeAll();
                    num = -1;
                } else hold(num, 3);
                num++;
                break;
            case R.id.f314:
                imagesetter(R.id.f314, 3, num);
                if (num == 2) {
                    closeAll();
                    num = -1;
                } else hold(num, 4);
                num++;
                break;
            case R.id.f315:
                imagesetter(R.id.f315, 4, num);
                if (num == 2) {
                    closeAll();
                    num = -1;
                } else hold(num, 5);
                num++;
                break;
            case R.id.f316:
                imagesetter(R.id.f316, 5, num);
                if (num == 2) {
                    closeAll();
                    num = -1;
                } else hold(num, 6);
                num++;
                break;
            case R.id.f321:
                imagesetter(R.id.f321, 6, num);
                if (num == 2) {
                    closeAll();
                    num = -1;
                } else hold(num, 7);
                num++;
                break;
            case R.id.f322:
                imagesetter(R.id.f322, 7, num);
                if (num == 2) {
                    closeAll();
                    num = -1;
                } else hold(num, 8);
                num++;
                break;
            case R.id.f323:
                imagesetter(R.id.f323, 8, num);
                if (num == 2) {
                    closeAll();
                    num = -1;
                } else hold(num, 9);
                num++;
                break;
            case R.id.f324:
                imagesetter(R.id.f324, 9, num);
                if (num == 2) {
                    closeAll();
                    num = -1;
                } else hold(num, 10);
                num++;
                break;
            case R.id.f325:
                imagesetter(R.id.f325, 10, num);
                if (num == 2) {
                    closeAll();
                    num = -1;
                } else hold(num, 11);
                num++;
                break;
            case R.id.f326:
                imagesetter(R.id.f326, 11, num);
                if (num == 2) {
                    closeAll();
                    num = -1;
                } else hold(num, 12);
                num++;
                break;
            case R.id.f331:
                imagesetter(R.id.f331, 12, num);
                if (num == 2) {
                    closeAll();
                    num = -1;
                } else hold(num, 13);
                num++;
                break;
            case R.id.f332:
                imagesetter(R.id.f332, 13, num);
                if (num == 2) {
                    closeAll();
                    num = -1;
                } else hold(num, 14);
                num++;
                break;
            case R.id.f333:
                imagesetter(R.id.f333, 14, num);
                if (num == 2) {
                    closeAll();
                    num = -1;
                } else hold(num, 15);
                num++;
                break;
            case R.id.f334:
                imagesetter(R.id.f334, 15, num);
                if (num == 2) {
                    closeAll();
                    num = -1;
                } else hold(num, 16);
                num++;
                break;
            case R.id.f335:
                imagesetter(R.id.f335, 16, num);
                if (num == 2) {
                    closeAll();
                    num = -1;
                } else hold(num, 17);
                num++;
                break;
            case R.id.f336:
                imagesetter(R.id.f336, 17, num);
                if (num == 2) {
                    closeAll();
                    num = -1;
                } else hold(num, 18);
                num++;
                break;
            case R.id.f341:
                imagesetter(R.id.f341, 18, num);
                if (num == 2) {
                    closeAll();
                    num = -1;
                } else hold(num, 19);
                num++;
                break;
            case R.id.f342:
                imagesetter(R.id.f342, 19, num);
                if (num == 2) {
                    closeAll();
                    num = -1;
                } else hold(num, 20);
                num++;
                break;
            case R.id.f343:
                imagesetter(R.id.f343, 20, num);
                if (num == 2) {
                    closeAll();
                    num = -1;
                } else hold(num, 21);
                num++;
                break;
            case R.id.f344:
                imagesetter(R.id.f344, 21, num);
                if (num == 2) {
                    closeAll();
                    num = -1;
                } else hold(num, 22);
                num++;
                break;
            case R.id.f345:
                imagesetter(R.id.f345, 22, num);
                if (num == 2) {
                    closeAll();
                    num = -1;
                } else hold(num, 23);
                num++;
                break;
            case R.id.f346:
                imagesetter(R.id.f346, 23, num);
                if (num == 2) {
                    closeAll();
                    num = -1;
                } else hold(num, 24);
                num++;
                break;
            case R.id.f351:
                imagesetter(R.id.f351, 24, num);
                if (num == 2) {
                    closeAll();
                    num = -1;
                } else hold(num, 25);
                num++;
                break;
            case R.id.f352:
                imagesetter(R.id.f352, 25, num);
                if (num == 2) {
                    closeAll();
                    num = -1;
                } else hold(num, 26);
                num++;
                break;
            case R.id.f353:
                imagesetter(R.id.f353, 26, num);
                if (num == 2) {
                    closeAll();
                    num = -1;
                } else hold(num, 27);
                num++;
                break;
            case R.id.f354:
                imagesetter(R.id.f354, 27, num);
                if (num == 2) {
                    closeAll();
                    num = -1;
                } else hold(num, 28);
                num++;
                break;
            case R.id.f355:
                imagesetter(R.id.f355, 28, num);
                if (num == 2) {
                    closeAll();
                    num = -1;
                } else hold(num, 29);
                num++;
                break;
            case R.id.f356:
                imagesetter(R.id.f356, 29, num);
                if (num == 2) {
                    closeAll();
                    num = -1;
                } else hold(num, 30);
                num++;
                break;
            case R.id.f361:
                imagesetter(R.id.f361, 30, num);
                if (num == 2) {
                    closeAll();
                    num = -1;
                } else hold(num, 31);
                num++;
                break;
            case R.id.f362:
                imagesetter(R.id.f362, 31, num);
                if (num == 2) {
                    closeAll();
                    num = -1;
                } else hold(num, 32);
                num++;
                break;
            case R.id.f363:
                imagesetter(R.id.f363, 32, num);
                if (num == 2) {
                    closeAll();
                    num = -1;
                } else hold(num, 33);
                num++;
                break;
            case R.id.f364:
                imagesetter(R.id.f364, 33, num);
                if (num == 2) {
                    closeAll();
                    num = -1;
                } else hold(num, 34);
                num++;
                break;
            case R.id.f365:
                imagesetter(R.id.f365, 34, num);
                if (num == 2) {
                    closeAll();
                    num = -1;
                } else hold(num, 35);
                num++;
                break;
            case R.id.f366:
                imagesetter(R.id.f366, 35, num);
                if (num == 2) {
                    closeAll();
                    num = -1;
                } else hold(num, 36);
                num++;
                break;
        }

    }


    public void hold(int number, int k) {

        int ref2 = flaglistNum[k - 1];
        number++;
        if (number == 1) {
            holderPic0 = k - 1;
            holder0 = ref2;
        } else if (number == 2) {
            holderPic1 = k - 1;
            holder1 = ref2;
            System.out.println("Trial : " + holder0 + " at " + holderPic0 + "and " + holder1 + " at " + holderPic1);
            if ((holder0 == holder1) && (holderPic0 != holderPic1)) {
                updatePoint(true);
                trueans3++;
                final ImageView tar1 = (ImageView) getActivity().findViewById(R.id.target1);
                final ImageView tar2 = (ImageView) getActivity().findViewById(R.id.target2);
                final ImageView tar3 = (ImageView) getActivity().findViewById(R.id.target3);
                final ImageView tar4 = (ImageView) getActivity().findViewById(R.id.target5);
                final ImageView tar5 = (ImageView) getActivity().findViewById(R.id.target6);
                final ImageView tar6 = (ImageView) getActivity().findViewById(R.id.target8);
                int t1 = flaglist.getInstance().getTarget31();
                int t2 = flaglist.getInstance().getTarget32();
                int t3 = flaglist.getInstance().getTarget33();
                int t4 = flaglist.getInstance().getTarget34();
                int t5 = flaglist.getInstance().getTarget35();
                int t6 = flaglist.getInstance().getTarget36();
                if (t1 == holder1) tar1.setImageResource(R.drawable.trueans);
                else if (t1 == holder1) tar1.setImageResource(R.drawable.trueans);
                else if (t2 == holder1) tar2.setImageResource(R.drawable.trueans);
                else if (t3 == holder1) tar3.setImageResource(R.drawable.trueans);
                else if (t4 == holder1) tar4.setImageResource(R.drawable.trueans);
                else if (t5 == holder1) tar5.setImageResource(R.drawable.trueans);
                else if (t6 == holder1) tar6.setImageResource(R.drawable.trueans);

                if (trueans3 == 6) {
                    Player.getInstance().setLife(4);


                    trueans3 = 0;
                    MemoryGameSelectionFragment move = new MemoryGameSelectionFragment();
                    android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.fragment_container, move);
                    ft.addToBackStack(null);
                    ft.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    ft.commit();
                    firebasePointUpdate(0);
                }
                holderPic0 = -2;
                holderPic1 = -3;
                holder0 = -4;
                holder1 = -5;
            } else {
                Player.getInstance().setLife(Player.getInstance().getLife() - 1);
                if (Player.getInstance().getLife() == 3) {
                    ImageView myim = (ImageView) getActivity().findViewById(R.id.h31);
                    myim.setImageResource(R.drawable.ekalp);
                } else if (Player.getInstance().getLife() == 2) {
                    ImageView myim = (ImageView) getActivity().findViewById(R.id.h32);
                    myim.setImageResource(R.drawable.ekalp);
                } else if (Player.getInstance().getLife() == 1) {
                    ImageView myim = (ImageView) getActivity().findViewById(R.id.h33);
                    myim.setImageResource(R.drawable.ekalp);
                } else if (Player.getInstance().getLife() == 0) {
                    ImageView myim = (ImageView) getActivity().findViewById(R.id.h34);
                    myim.setImageResource(R.drawable.ekalp);
                } else {
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
        image1.setImageBitmap(allpictures[k]);
    }

    public void closeAll() {

        for (int i = 0; i < 36; i++) {
            ImageView myview = (ImageView) getActivity().findViewById(imageViewID[i]);
            if (ac[i] == 0) {
                myview.setImageResource(R.drawable.card);
            }
        }
    }


    public void runthis() {
        for (int i = 0; i < 36; i++) {
            imagesetter(imageViewID[i], i, 1);
            Log.d("showthis", "runthis: " + i);
        }
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        StopwatchFragment stopwatchFragment = new StopwatchFragment();
        ft.replace(R.id.time_container3, stopwatchFragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
        dialog.dismiss();
        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            public void run() {
                try {
                    imagesettertarget(R.id.target1, 0, 1);
                    imagesettertarget(R.id.target2, 1, 1);
                    imagesettertarget(R.id.target3, 2, 1);
                    imagesettertarget(R.id.target5, 3, 1);
                    imagesettertarget(R.id.target6, 4, 1);
                    imagesettertarget(R.id.target8, 5, 1);
                    closeAll();
                } catch (Exception e) {

                }
            }
        }, 5000);
    }

    static int incrementer = 0;

    private int createImageArray(final int a[], final int[] flaglistNum, final Bitmap[] allpictures, final Bitmap[] targets) {

        try {
            if (checkConnection(getActivity().getApplicationContext())) {
                for (int i = 0; i < 30; i++) {
                    final File localFile = File.createTempFile("flag" + a[i] + ".png", "png");
                    storageRef = storage.getReference().child("flag" + a[i] + ".png");
                    final int finalI = i;
                    storageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            if (finalI < 6) {
                                targets[finalI] = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                allpictures[30 + finalI] = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                flaglistNum[30 + finalI] = a[finalI];
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
                            if (incrementer == 30) {
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
                        if (cnt < 30) {
                            byte[] image = cursor.getBlob(2);
                            Log.d("databaseInsert", "::: " + image);
                            Bitmap b = BitmapFactory.decodeByteArray(image, 0, image.length);
                            flaglistNum[cnt] = Integer.parseInt(cursor.getString(1));
                            allpictures[cnt] = b;
                            if (cnt < 6) {
                                flaglistNum[30 + cnt] = Integer.parseInt(cursor.getString(1));
                                allpictures[30 + cnt] = b;
                                targets[cnt] = b;
                                if (cnt == 0) {
                                    flaglist.getInstance().setTarget31(Integer.parseInt(cursor.getString(1)));
                                } else if (cnt == 1) {
                                    flaglist.getInstance().setTarget32(Integer.parseInt(cursor.getString(1)));
                                } else if (cnt == 2) {
                                    flaglist.getInstance().setTarget33(Integer.parseInt(cursor.getString(1)));
                                } else if (cnt == 3) {
                                    flaglist.getInstance().setTarget34(Integer.parseInt(cursor.getString(1)));
                                } else if (cnt == 4) {
                                    flaglist.getInstance().setTarget35(Integer.parseInt(cursor.getString(1)));
                                } else if (cnt == 5) {
                                    flaglist.getInstance().setTarget36(Integer.parseInt(cursor.getString(1)));
                                }
                            }
                            cnt++;
                            if (cnt == 30) {
                                runthis();
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
        }

        return 1;
    }

    public void firebasePointUpdate(final int errorSolver) {
        final int[] kk = {errorSolver};
        FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString()).child("info").child("mescore").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (kk[0] == 0) {
                    int usersCurrentScore = Integer.parseInt(dataSnapshot.getValue(String.class));
                    usersCurrentScore += Player.getInstance().getPlayerScore();
                    FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString()).child("info").child("mescore").setValue(usersCurrentScore + "");
                    kk[0]++;
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        if (challangeHandler.getInstance().isChallange()) {

            challangeHandler.getInstance().setIsChallange(false);
            if (challangeHandler.getInstance().isChallanger()) {
                FirebaseDatabase.getInstance().getReference("users").child(challangeHandler.getInstance().getMyId()).child("challanges").child(challangeHandler.getInstance().getOthersID()).child("scoreer1").setValue(Player.getInstance().getPlayerScore() + "");
                FirebaseDatabase.getInstance().getReference("users").child(challangeHandler.getInstance().getOthersID()).child("challanges").child(challangeHandler.getInstance().getMyId()).child("scoreer1").setValue(Player.getInstance().getPlayerScore() + "");
            } else {
                FirebaseDatabase.getInstance().getReference("users").child(challangeHandler.getInstance().getMyId()).child("challanges").child(challangeHandler.getInstance().getOthersID()).child("scoreer1").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() != null) {
                            QuestionFragment notifier = new QuestionFragment();
                            if (Integer.parseInt(dataSnapshot.getValue().toString()) > Player.getInstance().getPlayerScore()) {
                                Toast.makeText(getActivity(), "YOU LOST", Toast.LENGTH_SHORT).show();
                                notification.getInstance().sendNotification(challangeHandler.getChallangeremail(), "You win against " + Player.getInstance().getPlayerName());
                            } else if (Integer.parseInt(dataSnapshot.getValue().toString()) < Player.getInstance().getPlayerScore()) {
                                Toast.makeText(getActivity(), "YOU WIN", Toast.LENGTH_SHORT).show();
                                notification.getInstance().sendNotification(challangeHandler.getChallangeremail(), "You lost against " + Player.getInstance().getPlayerName());
                            } else {
                                Toast.makeText(getActivity(), "DRAW", Toast.LENGTH_SHORT).show();
                                notification.getInstance().sendNotification(challangeHandler.getChallangeremail(), "You are draw with " + Player.getInstance().getPlayerName());
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

    private void updatePoint(boolean k) {
        System.out.println("list members are " + holder0 + "and" + holder1);
        if (holder0 == holder1) {
            int alala = Player.getInstance().getPlayerScore();
            if (k) {
                alala = alala + 100;
                ac[holderPic0] = 1;
                ac[holderPic1] = 1;
            } else {
                alala = alala - 100;
            }

            Player.getInstance().setPlayerScore(alala);
            TextView textScore = (TextView) getActivity().findViewById(R.id.myscore5);
            String scoree = "SCORE " + alala;
            textScore.setText(scoree);

        }
    }
}





























