package com.example.astroidfield;

import java.util.ArrayList;

public class MyDB {

    private ArrayList<Record> records = new ArrayList<Record>();
    private Options options = new Options();

    public MyDB(){}

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
    public void setOptions(Options options) {
        this.options = options;
    }
}
