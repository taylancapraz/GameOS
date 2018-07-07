package com.example.canta.project3;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewUserProfileFragment extends Fragment implements View.OnClickListener{


    public ViewUserProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_view_user_profile, container, false);
    }
    TextView username, name, surname , city;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
        username = (TextView) getActivity().findViewById(R.id.other_profile_username);
        name = (TextView) getActivity().findViewById(R.id.other_profile_name);
        surname = (TextView) getActivity().findViewById(R.id.other_profile_surname);
        city = (TextView) getActivity().findViewById(R.id.other_profile_city);
        username.setText("Username:    " + Player_Other.getInstance().getPlayerName());
        name.setText("Name:        " + Player_Other.getInstance().getName());
        surname.setText("Surname:     " + Player_Other.getInstance().getSurname());
        city.setText("City:        " + Player_Other.getInstance().getCity());

        Button qq = (Button) getActivity().findViewById(R.id.challange_qq_user);
        Button mem4 = (Button) getActivity().findViewById(R.id.challange_44_user);
        Button mem5 = (Button) getActivity().findViewById(R.id.challange_55_user);
        Button mem6 = (Button) getActivity().findViewById(R.id.challange_66_user);
        qq.setOnClickListener(this);
        mem4.setOnClickListener(this);
        mem5.setOnClickListener(this);
        mem6.setOnClickListener(this);
    }

    String senderID,receiverID;
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.challange_qq_user:

                senderID = FirebaseAuth.getInstance().getCurrentUser().getUid().toString() + "";
                receiverID = Player_Other.getInstance().getId() + "";
                actionFirebase(senderID, receiverID, "quickquiz");
                Log.d("emailcheck", "onClick: " + Player_Other.getInstance().getEmail());

                int random = (int)Math.random() * 2;
                Log.d("qqqqq", "onClick: " + random);

                if (random ==0){
                    QuestionHolder.getInstance().setCategoryforChallange("hist");
                    QuestionHolder.getInstance().setCurrentQuestionNumberForChallange(0);
                    FirebaseDatabase.getInstance().getReference("users").child(senderID).child("challanges").child(receiverID).child("category").setValue("hist");
                    FirebaseDatabase.getInstance().getReference("users").child(receiverID).child("challanges").child(senderID).child("category").setValue("hist");

                }else if (random ==1){
                    QuestionHolder.getInstance().setCategoryforChallange("math");
                    QuestionHolder.getInstance().setCurrentQuestionNumberForChallange(0);
                    FirebaseDatabase.getInstance().getReference("users").child(senderID).child("challanges").child(receiverID).child("category").setValue("math");
                    FirebaseDatabase.getInstance().getReference("users").child(receiverID).child("challanges").child(senderID).child("category").setValue("math");

                }else if (random ==2){
                    QuestionHolder.getInstance().setCategoryforChallange("sport");
                    QuestionHolder.getInstance().setCurrentQuestionNumberForChallange(0);
                    FirebaseDatabase.getInstance().getReference("users").child(senderID).child("challanges").child(receiverID).child("category").setValue("sport");
                    FirebaseDatabase.getInstance().getReference("users").child(receiverID).child("challanges").child(senderID).child("category").setValue("sport");

                }

                qqChallangeHandler move1 = new qqChallangeHandler();
                android.app.FragmentTransaction ft1 = getFragmentManager().beginTransaction();
                ft1.replace(R.id.fragment_container, move1);
                ft1.addToBackStack(null);
                ft1.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft1.commit();
                break;
            case R.id.challange_44_user:
                senderID = FirebaseAuth.getInstance().getCurrentUser().getUid().toString() + "";
                receiverID = Player_Other.getInstance().getId() + "";
                actionFirebase(senderID, receiverID, "MemoGame 4x4");
                levelOneFragment move2 = new levelOneFragment();
                android.app.FragmentTransaction ft2 = getFragmentManager().beginTransaction();
                ft2.replace(R.id.fragment_container, move2);
                ft2.addToBackStack(null);
                ft2.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft2.commit();
                break;
            case R.id.challange_55_user:
                senderID = FirebaseAuth.getInstance().getCurrentUser().getUid().toString() + "";
                receiverID = Player_Other.getInstance().getId() + "";
                actionFirebase(senderID, receiverID, "MemoGame 5x5");
                levelTwoFragment move3 = new levelTwoFragment();
                android.app.FragmentTransaction ft3 = getFragmentManager().beginTransaction();
                ft3.replace(R.id.fragment_container, move3);
                ft3.addToBackStack(null);
                ft3.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft3.commit();
                break;
            case R.id.challange_66_user:
                senderID = FirebaseAuth.getInstance().getCurrentUser().getUid().toString() + "";
                receiverID = Player_Other.getInstance().getId() + "";
                actionFirebase(senderID, receiverID, "MemoGame 6x6");
                levelThreeFragment move4 = new levelThreeFragment();
                android.app.FragmentTransaction ft4 = getFragmentManager().beginTransaction();
                ft4.replace(R.id.fragment_container, move4);
                ft4.addToBackStack(null);
                ft4.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft4.commit();
                break;
        }
    }

    public void actionFirebase(String senderID, String receiverID, final String gametyle){

        challangeHandler.getInstance().setIsChallange(true);
        challangeHandler.getInstance().setMyId(senderID);
        challangeHandler.getInstance().setOthersID(receiverID);
        challangeHandler.getInstance().setIsChallanger(true);

        FirebaseDatabase.getInstance().getReference("users").child(senderID).child("challanges").child(receiverID).child("username").setValue(Player_Other.getInstance().getPlayerName());
        FirebaseDatabase.getInstance().getReference("users").child(senderID).child("challanges").child(receiverID).child("challanger").setValue(senderID);
        FirebaseDatabase.getInstance().getReference("users").child(senderID).child("challanges").child(receiverID).child("gametype").setValue(gametyle);
        FirebaseDatabase.getInstance().getReference("users").child(senderID).child("challanges").child(receiverID).child("scoreed2").setValue("0");
        FirebaseDatabase.getInstance().getReference("users").child(senderID).child("challanges").child(receiverID).child("scoreer1").setValue("0");
        FirebaseDatabase.getInstance().getReference("users").child(senderID).child("challanges").child(receiverID).child("challangeremail").setValue(FirebaseAuth.getInstance().getCurrentUser().getEmail().toString());
        FirebaseDatabase.getInstance().getReference("users").child(receiverID).child("challanges").child(senderID).child("username").setValue(Player.getInstance().getPlayerName());
        FirebaseDatabase.getInstance().getReference("users").child(receiverID).child("challanges").child(senderID).child("challanger").setValue(senderID);
        FirebaseDatabase.getInstance().getReference("users").child(receiverID).child("challanges").child(senderID).child("gametype").setValue(gametyle);
        FirebaseDatabase.getInstance().getReference("users").child(receiverID).child("challanges").child(senderID).child("scoreed2").setValue("0");
        FirebaseDatabase.getInstance().getReference("users").child(receiverID).child("challanges").child(senderID).child("scoreer1").setValue("0");
        FirebaseDatabase.getInstance().getReference("users").child(receiverID).child("challanges").child(senderID).child("challangeremail").setValue(FirebaseAuth.getInstance().getCurrentUser().getEmail().toString());

        FirebaseDatabase.getInstance().getReference("users").child(receiverID).child("info").child("email").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String email = dataSnapshot.getValue().toString();
                notification.getInstance().sendNotification(email, Player.getInstance().getPlayerName() + " sent a " + gametyle + " challange request");
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }





}
