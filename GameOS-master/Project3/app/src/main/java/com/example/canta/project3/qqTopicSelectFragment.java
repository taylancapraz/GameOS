package com.example.canta.project3;


import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class qqTopicSelectFragment extends Fragment implements View.OnClickListener {

    Button restartButton;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    public qqTopicSelectFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainActivity.toolbar.setTitle("Quick Quiz");

        if (checkConnection(getActivity().getApplicationContext())) {
                Log.d("databaseInsert", "internet var");
            database.getReference("quickquiz").child("sport").child("q1").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    QuestionDataSet.setQ1_sport(dataSnapshot.getValue(String.class));
                    MainActivity.mydb.insertData("sport", 1, dataSnapshot.getValue(String.class));
                }

                @Override
                public void onCancelled(DatabaseError error) {
                }
            });
            database.getReference("quickquiz").child("sport").child("q2").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    QuestionDataSet.setQ2_sport(dataSnapshot.getValue(String.class));
                    MainActivity.mydb.insertData("sport", 2, dataSnapshot.getValue(String.class));
                }

                @Override
                public void onCancelled(DatabaseError error) {
                }
            });
            database.getReference("quickquiz").child("sport").child("q3").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    QuestionDataSet.setQ3_sport(dataSnapshot.getValue(String.class));
                    MainActivity.mydb.insertData("sport", 3, dataSnapshot.getValue(String.class));
                }

                @Override
                public void onCancelled(DatabaseError error) {
                }
            });
            database.getReference("quickquiz").child("sport").child("q4").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    QuestionDataSet.setQ4_sport(dataSnapshot.getValue(String.class));
                    MainActivity.mydb.insertData("sport", 4, dataSnapshot.getValue(String.class));
                }

                @Override
                public void onCancelled(DatabaseError error) {
                }
            });
            database.getReference("quickquiz").child("sport").child("q5").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    QuestionDataSet.setQ5_sport(dataSnapshot.getValue(String.class));
                    MainActivity.mydb.insertData("sport", 5, dataSnapshot.getValue(String.class));
                }

                @Override
                public void onCancelled(DatabaseError error) {
                }
            });
            database.getReference("quickquiz").child("math").child("q1").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    QuestionDataSet.setQ1_math(dataSnapshot.getValue(String.class));
                    MainActivity.mydb.insertData("math", 1, dataSnapshot.getValue(String.class));
                }

                @Override
                public void onCancelled(DatabaseError error) {
                }
            });
            database.getReference("quickquiz").child("math").child("q2").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    QuestionDataSet.setQ2_math(dataSnapshot.getValue(String.class));
                    MainActivity.mydb.insertData("math", 2, dataSnapshot.getValue(String.class));
                }

                @Override
                public void onCancelled(DatabaseError error) {
                }
            });
            database.getReference("quickquiz").child("math").child("q3").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    QuestionDataSet.setQ3_math(dataSnapshot.getValue(String.class));
                    MainActivity.mydb.insertData("math", 3, dataSnapshot.getValue(String.class));
                }

                @Override
                public void onCancelled(DatabaseError error) {
                }
            });
            database.getReference("quickquiz").child("math").child("q4").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    QuestionDataSet.setQ4_math(dataSnapshot.getValue(String.class));
                    MainActivity.mydb.insertData("math", 4, dataSnapshot.getValue(String.class));
                }

                @Override
                public void onCancelled(DatabaseError error) {
                }
            });
            database.getReference("quickquiz").child("math").child("q5").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    QuestionDataSet.setQ5_math(dataSnapshot.getValue(String.class));
                    MainActivity.mydb.insertData("math", 5, dataSnapshot.getValue(String.class));
                }

                @Override
                public void onCancelled(DatabaseError error) {
                }
            });
            database.getReference("quickquiz").child("hist").child("q1").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    QuestionDataSet.setQ1_hist(dataSnapshot.getValue(String.class));
                    MainActivity.mydb.insertData("hist", 1, dataSnapshot.getValue(String.class));
                }

                @Override
                public void onCancelled(DatabaseError error) {
                }
            });
            database.getReference("quickquiz").child("hist").child("q2").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    QuestionDataSet.setQ2_hist(dataSnapshot.getValue(String.class));
                    MainActivity.mydb.insertData("hist", 2, dataSnapshot.getValue(String.class));
                }

                @Override
                public void onCancelled(DatabaseError error) {
                }
            });
            database.getReference("quickquiz").child("hist").child("q3").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    QuestionDataSet.setQ3_hist(dataSnapshot.getValue(String.class));
                    MainActivity.mydb.insertData("hist", 3, dataSnapshot.getValue(String.class));
                }

                @Override
                public void onCancelled(DatabaseError error) {
                }
            });
            database.getReference("quickquiz").child("hist").child("q4").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    QuestionDataSet.setQ4_hist(dataSnapshot.getValue(String.class));
                    MainActivity.mydb.insertData("hist", 4, dataSnapshot.getValue(String.class));
                }

                @Override
                public void onCancelled(DatabaseError error) {
                }
            });
            database.getReference("quickquiz").child("hist").child("q5").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    QuestionDataSet.setQ5_hist(dataSnapshot.getValue(String.class));
                    MainActivity.mydb.insertData("hist", 5, dataSnapshot.getValue(String.class));
                }

                @Override
                public void onCancelled(DatabaseError error) {
                }
            });
        }else{
            Log.d("databaseInsert", "ınternet yok");
            Cursor a = MainActivity.mydb.getData();
            if (a.getCount() == 0){
                Log.d("databaseInsert", "Cursor Null" );
            }else{
                StringBuffer buffer = new StringBuffer();
                while (a.moveToNext()){
                    if(a.getString(1).equals("hist") && (a.getString(2)+"").equals("1")){
                        QuestionDataSet.setQ1_hist(a.getString(3));
                    }
                    if(a.getString(1).equals("hist") && (a.getString(2)+"").equals("2")){
                        QuestionDataSet.setQ2_hist(a.getString(3));
                    }
                    if(a.getString(1).equals("hist") && (a.getString(2)+"").equals("3")){
                        QuestionDataSet.setQ3_hist(a.getString(3));
                    }
                    if(a.getString(1).equals("hist") && (a.getString(2)+"").equals("4")){
                        QuestionDataSet.setQ4_hist(a.getString(3));
                    }
                    if(a.getString(1).equals("hist") && (a.getString(2)+"").equals("5")){
                        QuestionDataSet.setQ5_hist(a.getString(3));
                    }
                    if(a.getString(1).equals("math") && (a.getString(2)+"").equals("1")){
                        QuestionDataSet.setQ1_math(a.getString(3));
                    }
                    if(a.getString(1).equals("math") && (a.getString(2)+"").equals("2")){
                        QuestionDataSet.setQ2_math(a.getString(3));
                    }
                    if(a.getString(1).equals("math") && (a.getString(2)+"").equals("3")){
                        QuestionDataSet.setQ3_math(a.getString(3));
                    }
                    if(a.getString(1).equals("math") && (a.getString(2)+"").equals("4")){
                        QuestionDataSet.setQ4_math(a.getString(3));
                    }
                    if(a.getString(1).equals("math") && (a.getString(2)+"").equals("5")){
                        QuestionDataSet.setQ5_math(a.getString(3));
                    }
                    if(a.getString(1).equals("sport") && (a.getString(2)+"").equals("1")){
                        QuestionDataSet.setQ1_sport(a.getString(3));
                    }
                    if(a.getString(1).equals("sport") && (a.getString(2)+"").equals("2")){
                        QuestionDataSet.setQ2_sport(a.getString(3));
                    }
                    if(a.getString(1).equals("sport") && (a.getString(2)+"").equals("3")){
                        QuestionDataSet.setQ3_sport(a.getString(3));
                    }
                    if(a.getString(1).equals("sport") && (a.getString(2)+"").equals("4")){
                        QuestionDataSet.setQ4_sport(a.getString(3));
                    }
                    if(a.getString(1).equals("sport") && (a.getString(2)+"").equals("5")){
                        QuestionDataSet.setQ5_sport(a.getString(3));
                    }
                }
            }
            Log.d("databaseInsert", "onCreate: " + a.toString() + "  " );
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_qq_topic_select, container, false);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onStart() {
        super.onStart();

        TextView playerName = (TextView) getActivity().findViewById(R.id.player_name);
        TextView scoreBoard = (TextView) getActivity().findViewById(R.id.score_topic);
        playerName.setText(Player.getInstance().getPlayerName());
        scoreBoard.setText("Score: " + Integer.toString(Player.getInstance().getPlayerScore()));
        ArrayList<Button> buttonList = new ArrayList<Button>();
        Button sportButton100 = (Button) getActivity().findViewById(R.id.sport_100);
        buttonList.add(sportButton100);
        Button sportButton200 = (Button) getActivity().findViewById(R.id.sport_200);
        buttonList.add(sportButton200);
        Button sportButton300 = (Button) getActivity().findViewById(R.id.sport_300);
        buttonList.add(sportButton300);
        Button sportButton400 = (Button) getActivity().findViewById(R.id.sport_400);
        buttonList.add(sportButton400);
        Button sportButton500 = (Button) getActivity().findViewById(R.id.sport_500);
        buttonList.add(sportButton500);
        Button mathButton100 = (Button) getActivity().findViewById(R.id.math_100);
        buttonList.add(mathButton100);
        Button mathButton200 = (Button) getActivity().findViewById(R.id.math_200);
        buttonList.add(mathButton200);
        Button mathButton300 = (Button) getActivity().findViewById(R.id.math_300);
        buttonList.add(mathButton300);
        Button mathButton400 = (Button) getActivity().findViewById(R.id.math_400);
        buttonList.add(mathButton400);
        Button mathButton500 = (Button) getActivity().findViewById(R.id.math_500);
        buttonList.add(mathButton500);
        Button historyButton100 = (Button) getActivity().findViewById(R.id.history_100);
        buttonList.add(historyButton100);
        Button historyButton200 = (Button) getActivity().findViewById(R.id.history_200);
        buttonList.add(historyButton200);
        Button historyButton300 = (Button) getActivity().findViewById(R.id.history_300);
        buttonList.add(historyButton300);
        Button historyButton400 = (Button) getActivity().findViewById(R.id.history_400);
        buttonList.add(historyButton400);
        Button historyButton500 = (Button) getActivity().findViewById(R.id.history_500);
        buttonList.add(historyButton500);
        restartButton = (Button) getActivity().findViewById(R.id.restart_button);

        for (int i = 0; i < buttonList.size(); i++) {
            if (QuestionHolder.getInstance().getQuestionList()[i] == 1) {
                System.out.println("Button " + i + " is disabled");
                buttonList.get(i).setBackground(getResources().getDrawable(R.drawable.button_true, getActivity().getTheme()));
                buttonList.get(i).setEnabled(false);
            } else if (QuestionHolder.getInstance().getQuestionList()[i] == 2) {
                System.out.println("Button " + i + " is disabled");
                buttonList.get(i).setBackground(getResources().getDrawable(R.drawable.button_wrong, getActivity().getTheme()));
                buttonList.get(i).setEnabled(false);
            } else if (QuestionHolder.getInstance().getQuestionList()[i] == 3) {
                System.out.println("Button " + i + " is disabled");
                buttonList.get(i).setBackground(getResources().getDrawable(R.drawable.button_go_back, getActivity().getTheme()));
                buttonList.get(i).setEnabled(false);
            } else {
                System.out.println("Button " + i + " is enabled");
                buttonList.get(i).setEnabled(true);
            }
        }

        sportButton100.setOnClickListener(this);
        sportButton200.setOnClickListener(this);
        sportButton300.setOnClickListener(this);
        sportButton400.setOnClickListener(this);
        sportButton500.setOnClickListener(this);

        mathButton100.setOnClickListener(this);
        mathButton200.setOnClickListener(this);
        mathButton300.setOnClickListener(this);
        mathButton400.setOnClickListener(this);
        mathButton500.setOnClickListener(this);

        historyButton100.setOnClickListener(this);
        historyButton200.setOnClickListener(this);
        historyButton300.setOnClickListener(this);
        historyButton400.setOnClickListener(this);
        historyButton500.setOnClickListener(this);

        restartButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.sport_100:
                questionDataUpdater(QuestionDataSet.getInstance().getQ1_sport(), 100, 1);
                break;

            case R.id.sport_200:
                questionDataUpdater(QuestionDataSet.getInstance().getQ2_sport(), 200, 1);
                break;

            case R.id.sport_300:
                questionDataUpdater(QuestionDataSet.getInstance().getQ3_sport(), 300, 1);
                break;

            case R.id.sport_400:
                questionDataUpdater(QuestionDataSet.getInstance().getQ4_sport(), 400, 1);
                break;

            case R.id.sport_500:
                questionDataUpdater(QuestionDataSet.getInstance().getQ5_sport(), 500, 1);
                break;

            case R.id.math_100:
                questionDataUpdater(QuestionDataSet.getInstance().getQ1_math(), 100, 2);
                break;

            case R.id.math_200:
                questionDataUpdater(QuestionDataSet.getInstance().getQ2_math(), 200, 2);
                break;

            case R.id.math_300:
                questionDataUpdater(QuestionDataSet.getInstance().getQ3_math(), 300, 2);
                break;

            case R.id.math_400:
                questionDataUpdater(QuestionDataSet.getInstance().getQ4_math(), 400, 2);
                break;

            case R.id.math_500:
                questionDataUpdater(QuestionDataSet.getInstance().getQ5_math(), 500, 2);
                break;

            case R.id.history_100:
                questionDataUpdater(QuestionDataSet.getInstance().getQ1_hist(), 100, 3);
                break;

            case R.id.history_200:
                questionDataUpdater(QuestionDataSet.getInstance().getQ2_hist(), 200, 3);
                break;

            case R.id.history_300:
                questionDataUpdater(QuestionDataSet.getInstance().getQ3_hist(), 300, 3);
                break;

            case R.id.history_400:
                questionDataUpdater(QuestionDataSet.getInstance().getQ4_hist(), 400, 3);
                break;

            case R.id.history_500:
                questionDataUpdater(QuestionDataSet.getInstance().getQ5_hist(), 500, 3);
                break;

            case R.id.restart_button:
                QuestionHolder.getInstance().questionListReset();
                Player.getInstance().setPlayerScore(0);

                qqTopicSelectFragment game1 = new qqTopicSelectFragment();
                android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, game1);
                ft.addToBackStack(null);
                ft.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
                //Intent intent = new Intent(getApplicationContext(), TopicSelectActivity.class);
                //startActivity(intent);
                //finish();
                break;

            default:
                break;
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
        android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, game1);
        ft.addToBackStack(null);
        ft.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();


        //Intent intent = new Intent(getApplicationContext(), QuestionActivity.class);
        //startActivity(intent);
        //finish();

    }

    public static boolean checkConnection(Context context) {
        final ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connMgr.getActiveNetworkInfo();

        if (activeNetworkInfo != null) {

            if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) {

                return true;
            } else if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {

                return true;
            }
        }
        return false;
    }










}
