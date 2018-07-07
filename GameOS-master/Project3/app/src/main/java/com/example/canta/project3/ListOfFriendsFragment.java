package com.example.canta.project3;

import android.app.ListFragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ListOfFriendsFragment extends ListFragment {
    static interface FriendsListener {
        void itemClicked(long id);
    }

    private ListOfFriendsFragment listener;


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    String[] names = new String[100];
    FirebaseUser currentFirebaseUser;
    DatabaseReference myRef;
    public long numChild = 0;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final int[] counter = {0};

        MainActivity.toolbar.setTitle("Your Friends");
        database.getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid() + "").child("friends").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                // Toast.makeText(getActivity(),"Text: " + dataSnapshot.getValue(), Toast.LENGTH_SHORT).show();
                if (dataSnapshot.getValue() != null) {
                    Player.getInstance().addFriend(dataSnapshot.getValue() + "", "1");
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        String[] listOfIDs = new String[Player.getInstance().getFriendCount()];
        for (int i = 0; i < Player.getInstance().getFriendCount(); i++) {
            listOfIDs[i] = Player.getInstance().getFriends()[i];
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                inflater.getContext(),
                android.R.layout.simple_list_item_1,
                listOfIDs);
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        String[] listOfUserID = new String[Player.getInstance().getFriendCount()];
        for (int i = 0; i < Player.getInstance().getFriendCount(); i++) {
            listOfUserID[i] = Player.getInstance().getAllFriendsID()[i];
        }
        //Toast.makeText(getActivity(), "Text: " + listOfUserID[(int) id], Toast.LENGTH_SHORT).show();
        Player_Other.getInstance().setId(listOfUserID[(int) id]);
        myRef = database.getReference("users").child(listOfUserID[(int) id]).child("info").child("username");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Player_Other.getInstance().setPlayerName("" + dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        myRef = database.getReference("users").child(listOfUserID[(int) id]).child("info").child("email");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Player_Other.getInstance().setEmail("" + dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        myRef = database.getReference("users").child(listOfUserID[(int) id]).child("info").child("name");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Player_Other.getInstance().setName("" + dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        myRef = database.getReference("users").child(listOfUserID[(int) id]).child("info").child("surname");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Player_Other.getInstance().setSurname("" + dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        myRef = database.getReference("users").child(listOfUserID[(int) id]).child("info").child("city");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Player_Other.getInstance().setCity("" + dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                ViewUserProfileFragment game1 = new ViewUserProfileFragment();
                android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, game1);
                ft.addToBackStack(null);
                ft.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        }, 1000);


    }


}














