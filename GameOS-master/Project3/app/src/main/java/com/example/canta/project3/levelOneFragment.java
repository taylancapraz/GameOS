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
import java.util.Random;

import static com.example.canta.project3.qqTopicSelectFragment.checkConnection;

public class levelOneFragment extends Fragment implements View.OnClickListener {
    int num = 0;
    int holder0;
    int holder1;
    int holderPic0;
    int holderPic1;
    int trueans = 0;
    int[] imageViewID = new int[20];

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference().child("android.jpg");

    int[] flaglistNum;
    int[] a = new int[12];
    int[] ac = new int[16];
    Bitmap[] allpictures;
    Bitmap[] targets;
    Dialog dialog;

    public levelOneFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_one_level, container, false);

        Player.getInstance().setLife(4);

        imageViewID[0] = R.id.f11;
        imageViewID[1] = R.id.f12;
        imageViewID[2] = R.id.f13;
        imageViewID[3] = R.id.f14;
        imageViewID[4] = R.id.f21;
        imageViewID[5] = R.id.f22;
        imageViewID[6] = R.id.f23;
        imageViewID[7] = R.id.f24;
        imageViewID[8] = R.id.f31;
        imageViewID[9] = R.id.f32;
        imageViewID[10] = R.id.f33;
        imageViewID[11] = R.id.f34;
        imageViewID[12] = R.id.f41;
        imageViewID[13] = R.id.f42;
        imageViewID[14] = R.id.f43;
        imageViewID[15] = R.id.f44;
        imageViewID[16] = R.id.imageView5;
        imageViewID[17] = R.id.imageView6;
        imageViewID[18] = R.id.imageView7;
        imageViewID[19] = R.id.imageView8;

        for (int i = 0; i < imageViewID.length; i++) {
            ImageView myImage = (ImageView) layout.findViewById(imageViewID[i]);
            myImage.setOnClickListener(this);
        }

        if (challangeHandler.isChallange() && challangeHandler.isChallanger()) {
            a = flaglist.getInstance().getQuestionList();
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

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        } else {
            a = flaglist.getInstance().getQuestionList();
        }

        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_loader);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();


        allpictures = new Bitmap[16];
        flaglistNum = new int[16];
        targets = new Bitmap[4];

        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            public void run() {
                createImageArray(a, flaglistNum, allpictures, targets);
            }
        }, 1000);


        return layout;
    }


    public void onStart() {
        super.onStart();
        for (int i = 0; i < ac.length; i++) {
            ac[i] = 0;
        }

        ImageView myim1 = (ImageView) getActivity().findViewById(R.id.h1);
        myim1.setImageResource(R.drawable.kalp);
        ImageView myim2 = (ImageView) getActivity().findViewById(R.id.h2);
        myim2.setImageResource(R.drawable.kalp);
        ImageView myim3 = (ImageView) getActivity().findViewById(R.id.h3);
        myim3.setImageResource(R.drawable.kalp);
        ImageView myim4 = (ImageView) getActivity().findViewById(R.id.h4);
        myim4.setImageResource(R.drawable.kalp);
        trueans = 0;

        final ImageView tar1 = (ImageView) getActivity().findViewById(R.id.imageView5);
        final ImageView tar2 = (ImageView) getActivity().findViewById(R.id.imageView6);
        final ImageView tar3 = (ImageView) getActivity().findViewById(R.id.imageView7);
        final ImageView tar4 = (ImageView) getActivity().findViewById(R.id.imageView8);

        tar1.setImageResource(R.drawable.card);
        tar2.setImageResource(R.drawable.card);
        tar3.setImageResource(R.drawable.card);
        tar4.setImageResource(R.drawable.card);

        Player.getInstance().setPlayerScore(0);

    }

    public void runThis() {
        dialog.dismiss();
        for (int i = 0; i < 16; i++) {
            imagesetter(imageViewID[i], i, 1);
        }
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        StopwatchFragment stopwatchFragment = new StopwatchFragment();
        ft.replace(R.id.time_container1, stopwatchFragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            public void run() {
                try {
                    closeAll();
                    imagesettertarget(R.id.imageView5, 0, 1);
                    imagesettertarget(R.id.imageView6, 1, 1);
                    imagesettertarget(R.id.imageView7, 2, 1);
                    imagesettertarget(R.id.imageView8, 3, 1);
                } catch (Exception e) {

                }
            }
        }, 5000);
    }

    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.f11:
                imagesetter(R.id.f11, 0, num);
                if (num == 2) {
                    closeAll();
                    num = -1;
                } else hold(num, 1);
                num++;
                break;
            case R.id.f12:
                imagesetter(R.id.f12, 1, num);
                if (num == 2) {
                    closeAll();
                    num = -1;
                } else hold(num, 2);
                num++;
                break;
            case R.id.f13:
                imagesetter(R.id.f13, 2, num);
                if (num == 2) {
                    closeAll();
                    num = -1;
                } else hold(num, 3);
                num++;
                break;
            case R.id.f14:
                imagesetter(R.id.f14, 3, num);
                if (num == 2) {
                    closeAll();
                    num = -1;
                } else hold(num, 4);
                num++;
                break;
            case R.id.f21:
                imagesetter(R.id.f21, 4, num);
                if (num == 2) {
                    closeAll();
                    num = -1;
                } else hold(num, 5);
                num++;
                break;
            case R.id.f22:
                imagesetter(R.id.f22, 5, num);
                if (num == 2) {
                    closeAll();
                    num = -1;
                } else hold(num, 6);
                num++;
                break;
            case R.id.f23:
                imagesetter(R.id.f23, 6, num);
                if (num == 2) {
                    closeAll();
                    num = -1;
                } else hold(num, 7);
                num++;
                break;
            case R.id.f24:
                imagesetter(R.id.f24, 7, num);
                if (num == 2) {
                    closeAll();
                    num = -1;
                } else hold(num, 8);
                num++;
                break;
            case R.id.f31:
                imagesetter(R.id.f31, 8, num);
                if (num == 2) {
                    closeAll();
                    num = -1;
                } else hold(num, 9);
                num++;
                break;
            case R.id.f32:
                imagesetter(R.id.f32, 9, num);
                if (num == 2) {
                    closeAll();
                    num = -1;
                } else hold(num, 10);
                num++;
                break;
            case R.id.f33:
                imagesetter(R.id.f33, 10, num);
                if (num == 2) {
                    closeAll();
                    num = -1;
                } else hold(num, 11);
                num++;
                break;
            case R.id.f34:
                imagesetter(R.id.f34, 11, num);
                if (num == 2) {
                    closeAll();
                    num = -1;
                } else hold(num, 12);
                num++;
                break;
            case R.id.f41:
                imagesetter(R.id.f41, 12, num);
                if (num == 2) {
                    closeAll();
                    num = -1;
                } else hold(num, 13);
                num++;
                break;
            case R.id.f42:
                imagesetter(R.id.f42, 13, num);
                if (num == 2) {
                    closeAll();
                    num = -1;
                } else hold(num, 14);
                num++;
                break;
            case R.id.f43:
                imagesetter(R.id.f43, 14, num);
                if (num == 2) {
                    closeAll();
                    num = -1;
                } else hold(num, 15);
                num++;
                break;
            case R.id.f44:
                imagesetter(R.id.f44, 15, num);
                if (num == 2) {
                    closeAll();
                    num = -1;
                } else hold(num, 16);
                num++;
                break;
        }
    }

    static int incrementer = 0;

    public void hold(int number, int k) {
        int ref = flaglistNum[k - 1];
        number++;
        if (number == 1) {
            holderPic0 = k - 1;
            holder0 = ref;
        } else if (number == 2) {
            holderPic1 = k - 1;
            holder1 = ref;
            System.out.println("Trial : " + holder0 + " at " + holderPic0 + "and " + holder1 + " at " + holderPic1);
            if ((holder0 == holder1) && (holderPic0 != holderPic1)) {
                updatePoint(true);
                trueans++;
                final ImageView tar1 = (ImageView) getActivity().findViewById(R.id.imageView5);
                final ImageView tar2 = (ImageView) getActivity().findViewById(R.id.imageView6);
                final ImageView tar3 = (ImageView) getActivity().findViewById(R.id.imageView7);
                final ImageView tar4 = (ImageView) getActivity().findViewById(R.id.imageView8);
                int t1 = flaglist.getInstance().getTarget1();
                int t2 = flaglist.getInstance().getTarget2();
                int t3 = flaglist.getInstance().getTarget3();
                int t4 = flaglist.getInstance().getTarget4();
                if (t1 == holder1) tar1.setImageResource(R.drawable.trueans);
                else if (t1 == holder1) tar1.setImageResource(R.drawable.trueans);
                else if (t2 == holder1) tar2.setImageResource(R.drawable.trueans);
                else if (t3 == holder1) tar3.setImageResource(R.drawable.trueans);
                else if (t4 == holder1) tar4.setImageResource(R.drawable.trueans);

                if (trueans == 4) {
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
            } else {
                Log.d("Clicked", "holderPic0: " + holderPic0);
                Log.d("Clicked", "holderPic1: " + holderPic1);
                Log.d("Clicked", "holder0: " + holder0);
                Log.d("Clicked", "holder1: " + holder1);
                Player.getInstance().setLife(Player.getInstance().getLife() - 1);
                if (Player.getInstance().getLife() == 3) {
                    ImageView myim = (ImageView) getActivity().findViewById(R.id.h1);
                    myim.setImageResource(R.drawable.ekalp);
                } else if (Player.getInstance().getLife() == 2) {
                    ImageView myim = (ImageView) getActivity().findViewById(R.id.h2);
                    myim.setImageResource(R.drawable.ekalp);
                } else if (Player.getInstance().getLife() == 1) {
                    ImageView myim = (ImageView) getActivity().findViewById(R.id.h3);
                    myim.setImageResource(R.drawable.ekalp);
                } else if (Player.getInstance().getLife() == 0) {
                    ImageView myim = (ImageView) getActivity().findViewById(R.id.h4);
                    myim.setImageResource(R.drawable.ekalp);
                } else {
                    Player.getInstance().setLife(4);
                    trueans = 0;
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

    public void closeAll() {

        for (int i = 0; i < 16; i++) {
            ImageView myview = (ImageView) getActivity().findViewById(imageViewID[i]);
            if (ac[i] == 0) {
                myview.setImageResource(R.drawable.card);
            }
        }
    }

    private int createImageArray(final int a[], final int[] flaglistNum, final Bitmap[] allpictures, final Bitmap[] targets) {

        try {
            if (checkConnection(getActivity().getApplicationContext())) {
                for (int i = 0; i < 12; i++) {
                    final File localFile = File.createTempFile("flag" + a[i] + ".png", "png");
                    storageRef = storage.getReference().child("flag" + a[i] + ".png");
                    final int finalI = i;
                    storageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            if (finalI < 4) {
                                targets[finalI] = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                allpictures[12 + finalI] = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                flaglistNum[12 + finalI] = a[finalI];
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

                            if (incrementer == 12) {

                                runThis();
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
                        if (cnt < 12) {
                            byte[] image = cursor.getBlob(2);
                            Log.d("taken from SQLite", "::: " + image);
                            Bitmap b = BitmapFactory.decodeByteArray(image, 0, image.length);
                            flaglistNum[cnt] = Integer.parseInt(cursor.getString(1));
                            allpictures[cnt] = b;
                            if (cnt < 4) {
                                flaglistNum[12 + cnt] = Integer.parseInt(cursor.getString(1));
                                allpictures[12 + cnt] = b;
                                targets[cnt] = b;
                                if (cnt == 0) {
                                    flaglist.getInstance().setTarget1(Integer.parseInt(cursor.getString(1)));
                                } else if (cnt == 1) {
                                    flaglist.getInstance().setTarget2(Integer.parseInt(cursor.getString(1)));
                                } else if (cnt == 2) {
                                    flaglist.getInstance().setTarget3(Integer.parseInt(cursor.getString(1)));
                                } else if (cnt == 3) {
                                    flaglist.getInstance().setTarget4(Integer.parseInt(cursor.getString(1)));
                                }
                            }
                            cnt++;
                            if (cnt == 12) {
                                runThis();
                            }
                        }
                    }
                }
            }

        } catch (IOException e) {
        }


        return 1;
    }

    private void shuffleArray(int[] flaglistNum, Bitmap[] allpictures) {
        int index;
        Bitmap temp;
        int temp2;
        Random random = new Random();
        for (int f = allpictures.length - 1; f > 0; f--) {
            index = random.nextInt(f + 1);
            temp = allpictures[index];
            temp2 = flaglistNum[index];
            allpictures[index] = allpictures[f];
            flaglistNum[index] = flaglistNum[f];
            allpictures[f] = temp;
            flaglistNum[f] = temp2;
            Log.d("mixarray", index + " is replaced with " + f);
        }
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
            TextView textScore = (TextView) getActivity().findViewById(R.id.myscore);
            String scoree = "SCORE " + alala;
            textScore.setText(scoree);

        }
    }
}

