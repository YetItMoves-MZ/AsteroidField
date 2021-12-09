package com.example.astroidfield;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MyDB {

    public static final String SP_FILE = "SP_FILE";
    private ArrayList<Record> records = new ArrayList<Record>();
    private Options options = new Options();

    public MyDB(){}

    public static MyDB getDB() {
        Gson gson = new Gson();
        String js = mySharedPreferences.getMe().getString("MY_DB", "");
        MyDB myDB = gson.fromJson(js, MyDB.class);
        if(myDB == null){ // will only enter on first time opening app
            myDB = new MyDB();
            myDB.setDefaultDB();
            //set json
            String json = new Gson().toJson(myDB);
            mySharedPreferences.getMe().putString("MY_DB", json);
        }
        return myDB;
    }

    public void setDB(){
        Gson gson = new Gson();
        String json = gson.toJson(this);
        mySharedPreferences.getMe().putString("MY_DB", json);
    }

    public ArrayList<Record> getRecords(){
        return records;
    }
    public Options getOptions() {
        return options;
    }

    public MyDB setRecords(ArrayList<Record> records){
        this.records = records;
        return this;
    }
    public MyDB setOptions(Options options) {
        this.options = options;
        return this;
    }
    
    public MyDB setDefaultDB(){
        this.options = new Options();
        this.options.setDefaultOptions();
        for(int i=0; i<10;i++){
            this.records.add(Record.defaultRecord());

        }
        return this;
    }
}
