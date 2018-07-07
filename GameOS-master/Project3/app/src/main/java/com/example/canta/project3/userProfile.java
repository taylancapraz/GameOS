package com.example.canta.project3;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
public class userProfile extends Fragment implements View.OnClickListener {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    FirebaseUser currentFirebaseUser;
    TextView username;
    EditText name;
    EditText surname;
    EditText city;
    Button submit_button;

    public userProfile() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        MainActivity.toolbar.setTitle("Your Profile");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_profile, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        submit_button = (Button) getActivity().findViewById(R.id.button_profile_save);
        submit_button.setOnClickListener(this);
        username = (TextView) getActivity().findViewById(R.id.profile_username);
        name = (EditText) getActivity().findViewById(R.id.profile_name);
        surname = (EditText) getActivity().findViewById(R.id.profile_surname);
        city = (EditText) getActivity().findViewById(R.id.profile_city);
        currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        myRef = database.getReference("users").child(currentFirebaseUser.getUid().toString()).child("info").child("username");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override public void onDataChange(DataSnapshot dataSnapshot) {username.setText(Player.getInstance().getPlayerName()); }
            @Override public void onCancelled(DatabaseError error) {}
        });
        myRef = database.getReference("users").child(currentFirebaseUser.getUid().toString()).child("info").child("name");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override public void onDataChange(DataSnapshot dataSnapshot) {name.setText(dataSnapshot.getValue(String.class));}
            @Override public void onCancelled(DatabaseError error) {}
        });
        myRef = database.getReference("users").child(currentFirebaseUser.getUid().toString()).child("info").child("surname");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override public void onDataChange(DataSnapshot dataSnapshot) {surname.setText(dataSnapshot.getValue(String.class));}
            @Override public void onCancelled(DatabaseError error) {}
        });
        myRef = database.getReference("users").child(currentFirebaseUser.getUid().toString()).child("info").child("city");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override public void onDataChange(DataSnapshot dataSnapshot) {city.setText(dataSnapshot.getValue(String.class));}
            @Override public void onCancelled(DatabaseError error) {}
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.button_profile_save: {

                System.out.println("clicked");
                myRef = database.getReference("users").child(currentFirebaseUser.getUid().toString()).child("info").child("name");
                myRef.setValue(name.getText() + "");
                myRef = database.getReference("users").child(currentFirebaseUser.getUid().toString()).child("info").child("surname");
                myRef.setValue(surname.getText() + "");
                myRef = database.getReference("users").child(currentFirebaseUser.getUid().toString()).child("info").child("city");
                myRef.setValue(city.getText() + "");



                ChallangesAndFriendRequestsFragment game1 = new ChallangesAndFriendRequestsFragment();
                android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, game1);
                ft.addToBackStack(null);
                ft.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
                break;
            }
        }
    }
}
