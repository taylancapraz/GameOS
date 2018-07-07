package com.example.canta.project3;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.onesignal.OneSignal;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import static com.example.canta.project3.MainActivity.current_user_email;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends Fragment {

    int timeInt = 11;

    Button answer1;
    Button answer2;
    Button answer3;
    Button answer4;

    TextView time;

    int scoreInt;
    int topicInt;
    int index;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    FirebaseUser currentFirebaseUser;

    boolean isNotOnPause = true;

    Handler handler;

    Runnable timeRunner = new Runnable() {

        public void run() {

            while (timeInt > 0) {
                try {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if(isNotOnPause) {
                            timeInt--;
                        }

                        if (timeInt >= 4 && timeInt < 7) {
                            time.setTextColor(ContextCompat.getColorStateList(getActivity().getApplicationContext(), R.color.myYellow));
                            time.setTextSize(55);
                        } else if (timeInt > 0 && timeInt < 4) {
                            time.setTextColor(ContextCompat.getColorStateList(getActivity().getApplicationContext(), R.color.myRed));
                            time.setTextSize(70);
                        } else if (timeInt <= 0) {
                            QuestionHolder.getInstance().questionSetter(index, 3);
                            goOtherActivity();
                        }

                        time.setText("" + timeInt);

                    }
                });


                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    };

    public QuestionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        OneSignal.startInit(getActivity())
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();

        OneSignal.sendTag("User_ID", current_user_email);


        return inflater.inflate(R.layout.fragment_question, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onStart(){
        super.onStart();


        scoreInt = Question.getInstance().getScore();
        topicInt = Question.getInstance().getTopic();

        index = (topicInt*5) + (scoreInt/100) - 6;

        TextView playerName = (TextView)getActivity().findViewById(R.id.player_name);
        TextView score = (TextView)getActivity().findViewById(R.id.score);
        TextView topic = (TextView)getActivity().findViewById(R.id.q_type);
        time = (TextView)getActivity().findViewById(R.id.q_time);
        TextView question = (TextView)getActivity().findViewById(R.id.question_text);

        score.setText("Score: " + Integer.toString(Player.getInstance().getPlayerScore()));

        startTimerThread();

        if(topicInt == 1){
            topic.setText("Sport " + scoreInt);
        }else if(topicInt == 2){
            topic.setText("Math " + scoreInt);
        }else if(topicInt == 3){
            topic.setText("History " + scoreInt);
        }else{

        }

        answer1 = (Button)getActivity().findViewById(R.id.answer1_q);
        answer2 = (Button)getActivity().findViewById(R.id.answer2_q);
        answer3 = (Button)getActivity().findViewById(R.id.answer3_q);
        answer4 = (Button)getActivity().findViewById(R.id.answer4_q);

        playerName.setText(Player.getInstance().getPlayerName());
        //score.setText(Player.getInstance().getPlayerScore());

        String k[] = new String[4];

        k[1] = Question.getInstance().getTrueAns();
        k[2] = Question.getInstance().getWrongAns1();
        k[3] = Question.getInstance().getWrongAns2();
        k[0] = Question.getInstance().getWrongAns3();

        shuffleArray(k);

        question.setText(Question.getInstance().getQuestion());

        answer1.setText(k[0]);
        answer2.setText(k[1]);
        answer3.setText(k[2]);
        answer4.setText(k[3]);

        answer1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                goNewActivity(answer1);

            }
        });

        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goNewActivity(answer2);

            }
        });

        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goNewActivity(answer3);
            }
        });

        answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goNewActivity(answer4);
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        handler.removeCallbacks(timeRunner);
    }

    @Override
    public void onPause() {
        super.onPause();
        isNotOnPause = false;
        handler.removeCallbacks(timeRunner);

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    static void shuffleArray(String[] arrayDeck)
    {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = ThreadLocalRandom.current();
        for (int i = arrayDeck.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            String a = arrayDeck[index];
            arrayDeck[index] = arrayDeck[i];
            arrayDeck[i] = a;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void goNewActivity(Button b){
        if(b.getText().toString().equals(Question.getTrueAns())){

            b.setBackground( getResources().getDrawable(R.drawable.button_true,getActivity().getTheme()));
            b.setTextColor(ContextCompat.getColorStateList(getActivity().getApplicationContext(), R.color.myWhite));
            Player.getInstance().setPlayerScore(Player.getInstance().getPlayerScore() + Question.getInstance().getScore());

            QuestionHolder.getInstance().questionSetter(index,1);
            timeInt = -1;
        }else{
            b.setBackground( getResources().getDrawable(R.drawable.button_wrong,getActivity().getTheme()));
            b.setTextColor(ContextCompat.getColorStateList(getActivity().getApplicationContext(), R.color.myWhite));
            QuestionHolder.getInstance().questionSetter(index,2);
            timeInt = -1;
        }

        goOtherActivity();
    }
    int usersCurrentScore;
    int errorSolver;
    public void goOtherActivity(){
        if (!isGameOver()) {

            if(challangeHandler.getInstance().isChallange()){
                qqChallangeHandler game1 = new qqChallangeHandler();
                android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, game1);
                ft.addToBackStack(null);
                ft.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }else {
                qqTopicSelectFragment game1 = new qqTopicSelectFragment();
                android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, game1);
                ft.addToBackStack(null);
                ft.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        } else {
            GameOverFragment game1 = new GameOverFragment();
            android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, game1);
            ft.addToBackStack(null);
            ft.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }
    }

    public boolean isGameOver(){

        for (int i = 0; i < 15; i++) {
            if(QuestionHolder.getInstance().getQuestionList()[i] == 0){
                return false;
            }
        }
        return true;
    }


    private void startTimerThread() {

        HandlerThread thread = new HandlerThread("MyHandlerThread");
        thread.start();

        handler = new Handler(thread.getLooper());
        handler.post(timeRunner);
    }

    public void sendNotification(String emailtoSend, String mymessage)
    {
        Log.d("emailtoSend", "sendNotification: " + emailtoSend);
        final String email = emailtoSend;
        final String message = mymessage;
        final String send_email = emailtoSend;
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                int SDK_INT = android.os.Build.VERSION.SDK_INT;

                if (SDK_INT > 8) {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                            .permitAll().build();
                    StrictMode.setThreadPolicy(policy);

                    try {
                        String jsonResponse;

                        URL url = new URL("https://onesignal.com/api/v1/notifications");
                        HttpURLConnection con = (HttpURLConnection) url.openConnection();
                        con.setUseCaches(false);
                        con.setDoOutput(true);
                        con.setDoInput(true);

                        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                        con.setRequestProperty("Authorization", "Basic MmI2NGI5OGItZGQyNC00NjNkLTllMzgtZmFmZTVhMmI5ZTUx");
                        con.setRequestMethod("POST");

                        String strJsonBody = "{"
                                + "\"app_id\": \"830ade25-7966-4efa-b780-4e392ebf3d6f\","

                                + "\"filters\": [{\"field\": \"tag\", \"key\": \"User_ID\", \"relation\": \"=\", \"value\": \"" + send_email + "\"}],"

                                + "\"data\": {\"foo\": \"bar\"},"
                                + "\"contents\": {\"en\": \""+ message + "\"}"
                                + "}";


                        System.out.println("strJsonBody:\n" + strJsonBody);

                        byte[] sendBytes = strJsonBody.getBytes("UTF-8");
                        con.setFixedLengthStreamingMode(sendBytes.length);

                        OutputStream outputStream = con.getOutputStream();
                        outputStream.write(sendBytes);

                        int httpResponse = con.getResponseCode();
                        System.out.println("httpResponse: " + httpResponse);

                        if (httpResponse >= HttpURLConnection.HTTP_OK
                                && httpResponse < HttpURLConnection.HTTP_BAD_REQUEST) {
                            Scanner scanner = new Scanner(con.getInputStream(), "UTF-8");
                            jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                            scanner.close();
                        } else {
                            Scanner scanner = new Scanner(con.getErrorStream(), "UTF-8");
                            jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                            scanner.close();
                        }
                        System.out.println("jsonResponse:\n" + jsonResponse);

                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
                }
            }
        });
    }

}
