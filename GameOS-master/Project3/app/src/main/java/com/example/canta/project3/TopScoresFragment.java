package com.example.canta.project3;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

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
public class TopScoresFragment extends Fragment {


    public TopScoresFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        MainActivity.toolbar.setTitle("Top Scores");
        return inflater.inflate(R.layout.fragment_top_scores, container, false);
    }

    final ArrayList<HashMap<String,String>> listqq = new ArrayList<HashMap<String,String>>();
    final ArrayList<HashMap<String,String>> listmg = new ArrayList<HashMap<String,String>>();
    ListView qqview;
    ListView mgview;
    SimpleAdapter adaptermg;
    SimpleAdapter adapterqq;
    FirebaseDatabase database;
    //String[] holder1 = new String[2];
    //String[] holder2 = new String[2];
    HashMap<String,String> holder1;
    HashMap<String,String> holder2;

    @Override
    public void onStart() {
        super.onStart();
        database = FirebaseDatabase.getInstance();
        qqview = (ListView)getActivity().findViewById(R.id.list_view_qq);
        mgview = (ListView)getActivity().findViewById(R.id.list_view_mg);


        adaptermg = new SimpleAdapter(getActivity(),
                listmg,
                R.layout.fragment_top_scores_mglist,
                new String[]{"username","mescore"},
                new int[]{R.id.textmgscore,R.id.textmgpoint});

        adapterqq = new SimpleAdapter(getActivity(),
                listqq,
                R.layout.fragment_top_scores_qqlist,
                new String[]{"username","qqscore"},
                new int[]{R.id.textqqscore,R.id.textqqpoint});

        qqview.setAdapter(adapterqq);
        mgview.setAdapter(adaptermg);

        database.getReference().child("users").addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot.getKey() != null) {
                    database.getReference().child("users").child(dataSnapshot.getKey().toString()).child("info").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            TopScore a = dataSnapshot.getValue(TopScore.class);
                            holder1 = new HashMap<String, String>();
                            holder2 = new HashMap<String, String>();
                            holder1.put("username" , a.getUsername());
                            holder1.put("qqscore" , a.getQqscore());
                            holder2.put("username" , a.getUsername());
                            holder2.put("mescore" , a.getMgscore());
                            Log.d("aaaaaaaaaaaa", "score is " + a.getMgscore());
                            listqq.add(holder1);
                            listmg.add(holder2);
                            sortHashmap(listqq,"qqscore");
                            sortHashmap(listmg,"mescore");
                            adapterqq.notifyDataSetChanged();
                            adaptermg.notifyDataSetChanged();

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
                }
            }


            public void sortHashmap(ArrayList<HashMap<String,String>> mymap, String gameName){
                Log.d("sorting", "size: " + mymap.size());
                    if (mymap.size() >= 3) {
                        HashMap<String, String> temp;   // Yer değiştirmede kullanılacak geçici değişken
                        for (int i = 1; i < mymap.size(); i++) {
                            for (int j = 0; j < mymap.size() - i; j++) {
                                if (mymap.get(j).get(gameName) != null) {
                                    if (Integer.parseInt(mymap.get(j).get(gameName)) < Integer.parseInt(mymap.get(j + 1).get(gameName))) {
                                        Log.d("sorting", mymap.get(j).get(gameName) + "  is replaced with " + mymap.get(j + 1).get(gameName));
                                        temp = mymap.get(j);
                                        mymap.set(j, mymap.get(j + 1));
                                        mymap.set(j + 1, temp);
                                    }
                                }
                            }
                        }
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

    }
}
