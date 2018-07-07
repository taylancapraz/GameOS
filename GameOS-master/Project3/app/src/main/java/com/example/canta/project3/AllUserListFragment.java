package com.example.canta.project3;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.List;


public class AllUserListFragment extends ListFragment {
    public static interface FriendsListener {
        void itemClicked(long id);
    }

    public AllUserListFragment listener;
    DatabaseReference myRef;

    Menu searchVar;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    String[] names = new String[100];
    public long numChild = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        MainActivity.menu_for_fragement.getItem(0).setVisible(true);
        MainActivity.toolbar.setTitle("All Users");
        MenuItem searchBar = MainActivity.menu_for_fragement.getItem(0);

        final int[] counter = {0};
        database.getReference("users").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(final DataSnapshot dataSnapshot, String s) {
                // Toast.makeText(getActivity(),"Text: " + dataSnapshot.getValue(), Toast.LENGTH_SHORT).show();
                if (dataSnapshot.getKey() != null) {
                    final String key = dataSnapshot.getKey().toString();
                    database.getReference("users").child(key).child("info").child("username").addValueEventListener(new ValueEventListener() {

                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot2) {
                            Player.getInstance().addUsertoList(dataSnapshot2.getValue().toString(), key);
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                        }
                    });
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

        String[] listOfUsername = new String[Player.getInstance().getUserCount()];
        String[] listOfUserID = new String[Player.getInstance().getUserCount()];
        for (int i = 0; i < Player.getInstance().getUserCount(); i++) {
            listOfUsername[i] = Player.getInstance().getAllPlayer()[i];
            listOfUserID[i] = Player.getInstance().getAllPlayerID()[i];
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                inflater.getContext(),
                android.R.layout.simple_list_item_1,
                listOfUsername);
        setListAdapter(adapter);

        SearchView searchView = (SearchView)searchBar.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.getFilter().filter(newText);

                return false;
            }
        });

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        Toast.makeText(getActivity(), "Click Username to Add Friend" , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onPause() {
        super.onPause();
        MainActivity.menu_for_fragement.getItem(0).setVisible(false);
    }

    @Override
    public void onStop() {
        super.onStop();
        MainActivity.menu_for_fragement.getItem(0).setVisible(false);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        String[] listOfUsername = new String[Player.getInstance().getUserCount()];
        String[] listOfUserID = new String[Player.getInstance().getUserCount()];
        for (int i = 0; i < Player.getInstance().getUserCount(); i++) {
            listOfUsername[i] = Player.getInstance().getAllPlayer()[i];
            listOfUserID[i] = Player.getInstance().getAllPlayerID()[i];
        }
        List valid = Arrays.asList(Player.getInstance().getFriends());

        if (listOfUsername[(int) id].equals(Player.getInstance().getPlayerName()) || valid.contains(listOfUsername[(int)id])){
            if(listOfUsername[(int) id].equals(Player.getInstance().getPlayerName())){
                Toast.makeText(getActivity(), "You cannot add yourself as a friend" , Toast.LENGTH_SHORT).show();
            }else if ( valid.contains(listOfUsername[(int)id])){
                Toast.makeText(getActivity(), "This user is already your friend" , Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(getActivity(), "This user is added as a friend: " + listOfUsername[(int) id], Toast.LENGTH_SHORT).show();
            database.getReference("users").child(listOfUserID[(int)id]).child("friendRequests").child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString()).child("username").setValue(Player.getInstance().getPlayerName());
            database.getReference("users").child(listOfUserID[(int)id]).child("friendRequests").child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString()).child("userID").setValue(FirebaseAuth.getInstance().getCurrentUser().getUid().toString());

            FirebaseDatabase.getInstance().getReference("users").child(listOfUserID[(int)id]).child("info").child("email").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String email = dataSnapshot.getValue().toString();
                    notification.getInstance().sendNotification(email, Player.getInstance().getPlayerName() + " added you as friend");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        }
    }
}














