package com.example.astroidfield;

import java.util.ArrayList;

public class MyDB {

    public static final String SP_FILE = "SP_FILE";
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
