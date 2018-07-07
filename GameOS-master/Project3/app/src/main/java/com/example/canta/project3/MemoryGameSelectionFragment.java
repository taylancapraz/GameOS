package com.example.canta.project3;


import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import static com.example.canta.project3.qqTopicSelectFragment.checkConnection;

/**
 * A simple {@link Fragment} subclass.
 */
public class MemoryGameSelectionFragment extends Fragment implements View.OnClickListener {

    public MemoryGameSelectionFragment() {
        // Required empty public constructor
    }

    Cursor cursor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        MainActivity.toolbar.setTitle("Memory Game");

        return inflater.inflate(R.layout.fragment_memory_game_selection, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Button play1 = (Button)getActivity().findViewById(R.id.playmemo4);
        Button play2 = (Button)getActivity().findViewById(R.id.playmemo5);
        Button play3 = (Button)getActivity().findViewById(R.id.playmemo6);
        cursor = MainActivity.mydb.getImage();
        play1.setOnClickListener(this);
        play2.setOnClickListener(this);
        play3.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.playmemo4:
                if (checkConnection(getActivity().getApplicationContext()) ||  (cursor.getCount() > 12) ) {
                    challangeHandler.getInstance().setIsChallange(false);
                    levelOneFragment move1 = new levelOneFragment();
                    android.app.FragmentTransaction ft1 = getFragmentManager().beginTransaction();
                    ft1.replace(R.id.fragment_container, move1);
                    ft1.addToBackStack(null);
                    ft1.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    ft1.commit();
                }else{
                    Toast.makeText(getActivity(),"Not Enough Flag in SQLite",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.playmemo5:
                if (checkConnection(getActivity().getApplicationContext()) ||  (cursor.getCount() > 20)) {
                    challangeHandler.getInstance().setIsChallange(false);
                    levelTwoFragment move2 = new levelTwoFragment();
                    android.app.FragmentTransaction ft1 = getFragmentManager().beginTransaction();
                    ft1.replace(R.id.fragment_container, move2);
                    ft1.addToBackStack(null);
                    ft1.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    ft1.commit();
                }else{
                    Toast.makeText(getActivity(),"Not Enough Flag in SQLite",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.playmemo6:
                if (checkConnection(getActivity().getApplicationContext()) ||  (cursor.getCount() > 30)) {
                challangeHandler.getInstance().setIsChallange(false);
                levelThreeFragment move3 = new levelThreeFragment();
                android.app.FragmentTransaction ft3 = getFragmentManager().beginTransaction();
                ft3.replace(R.id.fragment_container, move3);
                ft3.addToBackStack(null);
                ft3.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft3.commit();
                }else{
                    Toast.makeText(getActivity(),"Not Enough Flag in SQLite",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
