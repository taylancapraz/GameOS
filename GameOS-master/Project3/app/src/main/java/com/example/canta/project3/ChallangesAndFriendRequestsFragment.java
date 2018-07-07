package com.example.canta.project3;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChallangesAndFriendRequestsFragment extends Fragment{

    final ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
    final ArrayList<HashMap<String,String>> list2 = new ArrayList<HashMap<String,String>>();
    final ArrayList<String> listidler = new ArrayList<String>();
    final ArrayList<String> mailler = new ArrayList<String>();
    final ArrayList<String> categoriter = new ArrayList<String>();

    ListView challenges;
    ListView friendRequsts;
    SimpleAdapter adapter2;
    SimpleAdapter adapter;
    FirebaseDatabase database;

    ProgressBar bar;
    TextView textWarn;


    public ChallangesAndFriendRequestsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        MainActivity.toolbar.setTitle("Your Requests");
        challangeHandler.getInstance().setIsChallange(false);
        QuestionHolder.getInstance().setCurrentQuestionNumberForChallange(0);
        return inflater.inflate(R.layout.fragment_friend_list, container, false);
    }

    static String username = "holder";
    static String gametype = "holder";
    HashMap<String,String> holder;
    HashMap<String,String> holder2;
    int count = 0;
    @Override
    public void onStart(){
        super.onStart();

        textWarn = (TextView)getActivity().findViewById(R.id.warning_this);
        bar =(ProgressBar)getActivity().findViewById(R.id.progress_bar_main);
        database = FirebaseDatabase.getInstance();
        challenges = (ListView)getActivity().findViewById(R.id.list_view);
        friendRequsts = (ListView)getActivity().findViewById(R.id.list_view2);
        adapter = new SimpleAdapter(getActivity(),
                list,
                R.layout.list_item,
                new String[]{"First String", "Second String"},
                new int[]{R.id.text11,R.id.text22});
        adapter2 = new SimpleAdapter(getActivity(),
                list2,
                R.layout.list_viewer,
                new String[]{"First String", "Second String"},
                new int[]{R.id.text111,R.id.text222});
        challenges.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                Button k = (Button)view.findViewById(R.id.list_item_accept);
                Button ka = (Button)view.findViewById(R.id.list_item_decline);
                final int kemal = (int) id;
                k.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        challangeHandler.getInstance().setIsChallange(true);
                        challangeHandler.getInstance().setMyId(FirebaseAuth.getInstance().getCurrentUser().getUid().toString());
                        challangeHandler.getInstance().setOthersID(listidler.get(kemal));
                        challangeHandler.getInstance().setChallangeremail(mailler.get(kemal));
                        challangeHandler.getInstance().setIsChallanger(false);
                        QuestionHolder.getInstance().setCategoryforChallange(categoriter.get(kemal).toString());
                        Log.d("zzz", "onClick: " + QuestionHolder.getInstance().getCategoryforChallange());
                        startGame(list.get(kemal).get("Second String"));
                    }
                });
                ka.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String myid = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
                        String friendID = listidler.get(kemal);
                        database.getReference("users").child(myid).child("challanges").child(friendID).removeValue();
                        database.getReference("users").child(friendID).child("challanges").child(myid).removeValue();
                        refreshFragment();
                    }
                });
            }
        });

        friendRequsts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                Button k = (Button)view.findViewById(R.id.list_item_accept2);
                Button ka = (Button)view.findViewById(R.id.list_item_decline2);
                final int kemal = position;
                k.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        try {
                            String friendID = list2.get(kemal).get("Second String");
                            String myid = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
                            String friendUN = list2.get(kemal).get("First String");
                            String myUN = Player.getInstance().getPlayerName();
                            Log.d("AAAAA", "onClick: " + myid + "  " + friendID + "------" + list2.get(kemal));


                            database.getReference("users").child(myid).child("friends").child(friendID).setValue(friendUN);
                            database.getReference("users").child(friendID).child("friends").child(myid).setValue(myUN);
                            database.getReference("users").child(myid).child("friendRequests").child(friendID).removeValue();
                            database.getReference("users").child(friendID).child("info").child("email").addValueEventListener(new ValueEventListener() {


                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    notification.getInstance().sendNotification(dataSnapshot.getValue().toString(), Player.getPlayerName() + " accepted your friendship request!");
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });

                        }catch (Exception e){

                        }

                        refreshFragment();
                    }
                });
                ka.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String friendID = list2.get(kemal).get("Second String");
                        String myid = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
                        database.getReference("users").child(myid).child("friendRequests").child(friendID).removeValue();
                        refreshFragment();

                    }
                });
            }
        });


        friendRequsts.setAdapter(adapter2);
        challenges.setAdapter(adapter);
        count = 0;
        database.getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid() + "").child("challanges").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(final DataSnapshot dataSnapshot, String s) {
                // Toast.makeText(getActivity(),"Text: " + dataSnapshot.getValue(), Toast.LENGTH_SHORT).show();
                HoldTheDoor pencil = dataSnapshot.getValue(HoldTheDoor.class);

                    if(!FirebaseAuth.getInstance().getCurrentUser().getUid().toString().equals(pencil.getChallanger())) {
                        holder = new HashMap<String, String>();
                        holder.put("First String", pencil.getUsername());
                        holder.put("Second String", pencil.getGametype());
                        list.add(holder);
                        listidler.add(pencil.getChallanger());
                        mailler.add(pencil.getChallangeremail() + "");
                        categoriter.add(pencil.getCategory());
                        adapter.notifyDataSetChanged();

                        Log.d("donee", "onChildAdded: " + count + "  --  " + dataSnapshot.getChildrenCount());
                        if(count == dataSnapshot.getChildrenCount()){
                            Log.d("donee", "onChildAdded: done");
                        }
                        count ++;

                        bar.setVisibility(View.INVISIBLE);
                        textWarn.setVisibility(View.INVISIBLE);
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

        database.getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid() + "").child("friendRequests").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                DeneryosTargeryos pencil = dataSnapshot.getValue(DeneryosTargeryos.class);

                    holder2 = new HashMap<String, String>();
                    holder2.put("First String", pencil.getUsername());
                    holder2.put("Second String", pencil.getUserID());
                    list2.add(holder2);
                    adapter2.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                refreshFragment();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void startGame(String gamename){
        try {
            if (gamename.equals("quickquiz")) {
                qqChallangeHandler game1 = new qqChallangeHandler();
                android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, game1);
                ft.addToBackStack(null);
                ft.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            } else if (gamename.equals("MemoGame 4x4")) {
                levelOneFragment game1 = new levelOneFragment();
                android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, game1);
                ft.addToBackStack(null);
                ft.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();

            } else if (gamename.equals("MemoGame 5x5")) {
                levelTwoFragment game1 = new levelTwoFragment();
                android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, game1);
                ft.addToBackStack(null);
                ft.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            } else if (gamename.equals("MemoGame 6x6")) {
                levelThreeFragment game1 = new levelThreeFragment();
                android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, game1);
                ft.addToBackStack(null);
                ft.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            } else {
                Log.d("invalid", "startGame: " + gamename);
            }
        }catch(Exception e){
            refreshFragment();
        }
    }

    public void refreshFragment(){

        ChallangesAndFriendRequestsFragment game1 = new ChallangesAndFriendRequestsFragment();
        android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, game1);
        ft.addToBackStack(null);
        ft.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

}
