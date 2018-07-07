package com.example.canta.project3;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameOverFragment extends Fragment{
    Button playQ;
    public GameOverFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //getActivity().getActionBar().hide();

        View layout = inflater.inflate(R.layout.fragment_game_over, container, false);

        return inflater.inflate(R.layout.fragment_game_over, container, false);
    }
    public void onStart(){
        super.onStart();
        TextView myScore = (TextView) getActivity().findViewById(R.id.overscore);
        String myText = "" + Player.getInstance().getPlayerScore();
        System.out.println(myText);
        myScore.setText(myText);
    }
}
