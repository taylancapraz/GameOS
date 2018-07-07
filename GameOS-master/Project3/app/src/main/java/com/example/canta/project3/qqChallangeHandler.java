package com.example.canta.project3;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class qqChallangeHandler extends Fragment {
    int errorSolver = 0;
    int usersCurrentScore;
    DatabaseReference myRef;

    public qqChallangeHandler() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText(R.string.hello_blank_fragment);
        return textView;
    }

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        database.getReference("quickquiz").child("sport").child("q1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                QuestionDataSet.setQ1_sport(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
        database.getReference("quickquiz").child("sport").child("q2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                QuestionDataSet.setQ2_sport(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
        database.getReference("quickquiz").child("sport").child("q3").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                QuestionDataSet.setQ3_sport(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
        database.getReference("quickquiz").child("sport").child("q4").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                QuestionDataSet.setQ4_sport(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
        database.getReference("quickquiz").child("sport").child("q5").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                QuestionDataSet.setQ5_sport(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
        database.getReference("quickquiz").child("math").child("q1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                QuestionDataSet.setQ1_math(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
        database.getReference("quickquiz").child("math").child("q2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                QuestionDataSet.setQ2_math(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
        database.getReference("quickquiz").child("math").child("q3").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                QuestionDataSet.setQ3_math(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
        database.getReference("quickquiz").child("math").child("q4").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                QuestionDataSet.setQ4_math(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
        database.getReference("quickquiz").child("math").child("q5").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                QuestionDataSet.setQ5_math(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
        database.getReference("quickquiz").child("hist").child("q1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                QuestionDataSet.setQ1_hist(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
        database.getReference("quickquiz").child("hist").child("q2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                QuestionDataSet.setQ2_hist(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
        database.getReference("quickquiz").child("hist").child("q3").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                QuestionDataSet.setQ3_hist(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
        database.getReference("quickquiz").child("hist").child("q4").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                QuestionDataSet.setQ4_hist(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
        database.getReference("quickquiz").child("hist").child("q5").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                QuestionDataSet.setQ5_hist(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });


        if (QuestionHolder.getInstance().getCategoryforChallange().equals("sport")) {
            if (QuestionHolder.getInstance().getCurrentQuestionNumberForChallange() == 0) {
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    public void run() {
                        QuestionHolder.getInstance().setCurrentQuestionNumberForChallange(1);
                        questionDataUpdater(QuestionDataSet.getInstance().getQ1_sport(), 100, 1);
                    }
                }, 2000);
            } else if (QuestionHolder.getInstance().getCurrentQuestionNumberForChallange() == 1) {
                QuestionHolder.getInstance().setCurrentQuestionNumberForChallange(2);
                questionDataUpdater(QuestionDataSet.getInstance().getQ2_sport(), 100, 1);
            } else if (QuestionHolder.getInstance().getCurrentQuestionNumberForChallange() == 2) {
                QuestionHolder.getInstance().setCurrentQuestionNumberForChallange(3);
                questionDataUpdater(QuestionDataSet.getInstance().getQ3_sport(), 100, 1);
            } else if (QuestionHolder.getInstance().getCurrentQuestionNumberForChallange() == 3) {
                QuestionHolder.getInstance().setCurrentQuestionNumberForChallange(4);
                questionDataUpdater(QuestionDataSet.getInstance().getQ4_sport(), 100, 1);
            } else if (QuestionHolder.getInstance().getCurrentQuestionNumberForChallange() == 4) {
                QuestionHolder.getInstance().setCurrentQuestionNumberForChallange(5);
                questionDataUpdater(QuestionDataSet.getInstance().getQ5_sport(), 100, 1);
            } else if (QuestionHolder.getInstance().getCurrentQuestionNumberForChallange() == 5) {
                resultTeller();
            }
        } else if (QuestionHolder.getInstance().getCategoryforChallange().equals("math")) {
            if (QuestionHolder.getInstance().getCurrentQuestionNumberForChallange() == 0) {

                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    public void run() {
                        QuestionHolder.getInstance().setCurrentQuestionNumberForChallange(1);
                        questionDataUpdater(QuestionDataSet.getInstance().getQ1_math(), 100, 2);
                    }
                }, 2000);
            } else if (QuestionHolder.getInstance().getCurrentQuestionNumberForChallange() == 1) {
                QuestionHolder.getInstance().setCurrentQuestionNumberForChallange(2);
                questionDataUpdater(QuestionDataSet.getInstance().getQ2_math(), 100, 2);
            } else if (QuestionHolder.getInstance().getCurrentQuestionNumberForChallange() == 2) {
                QuestionHolder.getInstance().setCurrentQuestionNumberForChallange(3);
                questionDataUpdater(QuestionDataSet.getInstance().getQ3_math(), 100, 2);
            } else if (QuestionHolder.getInstance().getCurrentQuestionNumberForChallange() == 3) {
                QuestionHolder.getInstance().setCurrentQuestionNumberForChallange(4);
                questionDataUpdater(QuestionDataSet.getInstance().getQ4_math(), 100, 2);
            } else if (QuestionHolder.getInstance().getCurrentQuestionNumberForChallange() == 4) {
                QuestionHolder.getInstance().setCurrentQuestionNumberForChallange(5);
                questionDataUpdater(QuestionDataSet.getInstance().getQ5_math(), 100, 2);
            } else if (QuestionHolder.getInstance().getCurrentQuestionNumberForChallange() == 5) {
                resultTeller();
            }
        } else if (QuestionHolder.getInstance().getCategoryforChallange().equals("hist")) {
            if (QuestionHolder.getInstance().getCurrentQuestionNumberForChallange() == 0) {
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    public void run() {
                        QuestionHolder.getInstance().setCurrentQuestionNumberForChallange(1);
                        questionDataUpdater(QuestionDataSet.getInstance().getQ1_sport(), 100, 3);
                    }
                }, 2000);

            } else if (QuestionHolder.getInstance().getCurrentQuestionNumberForChallange() == 1) {
                QuestionHolder.getInstance().setCurrentQuestionNumberForChallange(2);
                questionDataUpdater(QuestionDataSet.getInstance().getQ2_sport(), 100, 3);
            } else if (QuestionHolder.getInstance().getCurrentQuestionNumberForChallange() == 2) {
                QuestionHolder.getInstance().setCurrentQuestionNumberForChallange(3);
                questionDataUpdater(QuestionDataSet.getInstance().getQ3_sport(), 100, 3);
            } else if (QuestionHolder.getInstance().getCurrentQuestionNumberForChallange() == 3) {
                QuestionHolder.getInstance().setCurrentQuestionNumberForChallange(4);
                questionDataUpdater(QuestionDataSet.getInstance().getQ4_sport(), 100, 3);
            } else if (QuestionHolder.getInstance().getCurrentQuestionNumberForChallange() == 4) {
                QuestionHolder.getInstance().setCurrentQuestionNumberForChallange(5);
                questionDataUpdater(QuestionDataSet.getInstance().getQ5_sport(), 100, 3);
            } else if (QuestionHolder.getInstance().getCurrentQuestionNumberForChallange() == 5) {
                resultTeller();
            }
        }
    }


    private void questionDataUpdater(String data, int score, int topic) {
        String[] parts = data.split("-");
        String part1 = parts[0];
        String part2 = parts[1];
        String part3 = parts[2];
        String part4 = parts[3];
        String part5 = parts[4];
        Question.getInstance().setTopic(topic);
        Question.getInstance().setQuestion(part1);
        Question.getInstance().setWrongAns1(part2);
        Question.getInstance().setWrongAns2(part3);
        Question.getInstance().setWrongAns3(part4);
        Question.getInstance().setTrueAns(part5);
        Question.getInstance().setScore(score);
        QuestionFragment game1 = new QuestionFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, game1);
        ft.addToBackStack(null);
        ft.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

    public void resultTeller() {
        QuestionHolder.getInstance().setCurrentQuestionNumberForChallange(0);
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        myRef = database.getReference("users").child(currentFirebaseUser.getUid().toString()).child("info").child("qqscore");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (errorSolver == 0) {
                    usersCurrentScore = Integer.parseInt(dataSnapshot.getValue(String.class));
                    usersCurrentScore += Player.getInstance().getPlayerScore();
                    myRef.setValue(usersCurrentScore + "");
                    errorSolver++;
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
        if (challangeHandler.getInstance().isChallange()) {
            challangeHandler.getInstance().setIsChallange(false);
            if (challangeHandler.getInstance().isChallanger()) {
                database.getReference("users").child(challangeHandler.getInstance().getMyId()).child("challanges").child(challangeHandler.getInstance().getOthersID()).child("scoreer1").setValue(Player.getInstance().getPlayerScore() + "");
                database.getReference("users").child(challangeHandler.getInstance().getOthersID()).child("challanges").child(currentFirebaseUser.getUid().toString()).child("scoreer1").setValue(Player.getInstance().getPlayerScore() + "");
            } else {
                database.getReference("users").child(challangeHandler.getInstance().getMyId()).child("challanges").child(challangeHandler.getInstance().getOthersID()).child("scoreer1").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() != null) {
                            if (Integer.parseInt(dataSnapshot.getValue().toString()) > Player.getInstance().getPlayerScore()) {
                                Toast.makeText(getActivity(), "YOU LOST", Toast.LENGTH_SHORT).show();
                                notification.getInstance().sendNotification(challangeHandler.getChallangeremail(), "YOU WIN AGAINST " + Player.getInstance().getPlayerName() + " in QuickQuiz");
                            } else if (Integer.parseInt(dataSnapshot.getValue().toString()) < Player.getInstance().getPlayerScore()) {
                                Toast.makeText(getActivity(), "YOU WIN", Toast.LENGTH_SHORT).show();
                                notification.getInstance().sendNotification(challangeHandler.getChallangeremail(), "YOU LOST AGAINST " + Player.getInstance().getPlayerName() + " in QuickQuiz");
                            } else {
                                Toast.makeText(getActivity(), "DRAW", Toast.LENGTH_SHORT).show();
                                notification.getInstance().sendNotification(challangeHandler.getChallangeremail(), "YOU ARE DRAW WITH " + Player.getInstance().getPlayerName() + " in QuickQuiz");
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                String myid = challangeHandler.getInstance().getMyId();
                String friendID = challangeHandler.getInstance().getOthersID();
                database.getReference("users").child(myid).child("challanges").child(friendID).removeValue();
                database.getReference("users").child(friendID).child("challanges").child(myid).removeValue();
            }
        }

        challangeHandler.getInstance().setIsChallange(false);
        GameOverFragment game1 = new GameOverFragment();
        android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, game1);
        ft.addToBackStack(null);
        ft.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

}
