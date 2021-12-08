package com.example.astroidfield;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;



public class Leaderboards_Score_Fragment extends Fragment {

    public static final int LEADERBOARDS_SIZE = 10;

    private TextView names[];
    private TextView scores[];
    private TextView odometers[];
    private Button showLocations[];
    private double lons[] = new double[LEADERBOARDS_SIZE];
    private double lats[] = new double[LEADERBOARDS_SIZE];
    private String stringName[] = new String[LEADERBOARDS_SIZE];


    private CallBack_Leaderboards_Scores callBack;
    private AppCompatActivity activity;




    public void setActivity(AppCompatActivity activity) {
        this.activity = activity;
    }
    public void setCallBackMap(CallBack_Leaderboards_Scores callBack){
        this.callBack = callBack;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_leaderboards__score, container, false);
        findViews(view);
        initViews();
        setScores(MyDB.getDB().getRecords());


        return view;
    }

    private void setScores(ArrayList<Record> records) {
        for(int i=0; i<LEADERBOARDS_SIZE;i++){
            Record record = records.get(i);
            stringName[i] = record.getName();
            names[i].setText(stringName[i]);
            scores[i].setText(""+record.getScore());
            odometers[i].setText(""+record.getOdometer());
            lons[i] = record.getLon();
            lats[i] = record.getLat();

        }
    }
    private void sendLocation(double lon, double lat, String name) {
        callBack.mapPing(lon,lat,name);
    }


    private void initViews() {
        showLocations[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendLocation(lons[0],lats[0],stringName[0]);
            }
        });
        showLocations[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendLocation(lons[1],lats[1],stringName[1]);
            }
        });
        showLocations[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendLocation(lons[2],lats[2],stringName[2]);
            }
        });
        showLocations[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendLocation(lons[3],lats[3],stringName[3]);
            }
        });
        showLocations[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendLocation(lons[4],lats[4],stringName[4]);
            }
        });
        showLocations[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendLocation(lons[5],lats[5],stringName[5]);
            }
        });
        showLocations[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendLocation(lons[6],lats[6],stringName[6]);
            }
        });
        showLocations[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendLocation(lons[7],lats[7],stringName[7]);
            }
        });
        showLocations[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendLocation(lons[8],lats[8],stringName[8]);
            }
        });
        showLocations[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendLocation(lons[9],lats[9],stringName[9]);
            }
        });

    }
    private void findViews(View view) {
        names = new TextView[]{
                view.findViewById(R.id.leaderboards_score_TXT_name1),
                view.findViewById(R.id.leaderboards_score_TXT_name2),
                view.findViewById(R.id.leaderboards_score_TXT_name3),
                view.findViewById(R.id.leaderboards_score_TXT_name4),
                view.findViewById(R.id.leaderboards_score_TXT_name5),
                view.findViewById(R.id.leaderboards_score_TXT_name6),
                view.findViewById(R.id.leaderboards_score_TXT_name7),
                view.findViewById(R.id.leaderboards_score_TXT_name8),
                view.findViewById(R.id.leaderboards_score_TXT_name9),
                view.findViewById(R.id.leaderboards_score_TXT_name10)

        };
        scores = new TextView[]{
                view.findViewById(R.id.leaderboards_score_TXT_score1),
                view.findViewById(R.id.leaderboards_score_TXT_score2),
                view.findViewById(R.id.leaderboards_score_TXT_score3),
                view.findViewById(R.id.leaderboards_score_TXT_score4),
                view.findViewById(R.id.leaderboards_score_TXT_score5),
                view.findViewById(R.id.leaderboards_score_TXT_score6),
                view.findViewById(R.id.leaderboards_score_TXT_score7),
                view.findViewById(R.id.leaderboards_score_TXT_score8),
                view.findViewById(R.id.leaderboards_score_TXT_score9),
                view.findViewById(R.id.leaderboards_score_TXT_score10)

        };
        odometers = new TextView[]{
                view.findViewById(R.id.leaderboards_score_TXT_odometer1),
                view.findViewById(R.id.leaderboards_score_TXT_odometer2),
                view.findViewById(R.id.leaderboards_score_TXT_odometer3),
                view.findViewById(R.id.leaderboards_score_TXT_odometer4),
                view.findViewById(R.id.leaderboards_score_TXT_odometer5),
                view.findViewById(R.id.leaderboards_score_TXT_odometer6),
                view.findViewById(R.id.leaderboards_score_TXT_odometer7),
                view.findViewById(R.id.leaderboards_score_TXT_odometer8),
                view.findViewById(R.id.leaderboards_score_TXT_odometer9),
                view.findViewById(R.id.leaderboards_score_TXT_odometer10)

        };
        showLocations = new Button[]{
                view.findViewById(R.id.leaderboards_score_BTN_ShowOnMap1),
                view.findViewById(R.id.leaderboards_score_BTN_ShowOnMap2),
                view.findViewById(R.id.leaderboards_score_BTN_ShowOnMap3),
                view.findViewById(R.id.leaderboards_score_BTN_ShowOnMap4),
                view.findViewById(R.id.leaderboards_score_BTN_ShowOnMap5),
                view.findViewById(R.id.leaderboards_score_BTN_ShowOnMap6),
                view.findViewById(R.id.leaderboards_score_BTN_ShowOnMap7),
                view.findViewById(R.id.leaderboards_score_BTN_ShowOnMap8),
                view.findViewById(R.id.leaderboards_score_BTN_ShowOnMap9),
                view.findViewById(R.id.leaderboards_score_BTN_ShowOnMap10)
        };
    }
}