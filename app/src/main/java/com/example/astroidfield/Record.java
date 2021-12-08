package com.example.astroidfield;

public class Record implements Comparable<Record>{


    private String name = "";
    private int odometer = -1;
    private int score = -1;
    private double lat = 0.0;
    private double lon = 0.0;

    public Record() {}

    public Record(String name, int odometer, int score, double lat, double lon){
        this.name=name;
        this.odometer=odometer;
        this.score=score;
        this.lat=lat;
        this.lon=lon;
    }


    public int getOdometer() {
        return odometer;
    }
    public int getScore() {
        return score;
    }
    public double getLat() {
        return lat;
    }
    public double getLon() {
        return lon;
    }
    public String getName() {
        return name;
    }

    public Record setName(String name) {
        this.name = name;
        return this;
    }
    public Record setOdometer(int odometer) {
        this.odometer = odometer;
        return this;
    }
    public Record setScore(int score) {
        this.score = score;
        return this;
    }
    public Record setLat(double lat) {
        this.lat = lat;
        return this;
    }
    public Record setLon(double lon) {
        this.lon = lon;
        return this;
    }

    public static Record defaultRecord() {
        Record record = new Record();
        return record.setLat(0).setLon(0).setOdometer(0).setScore(-1).setName("noName");
    }

    @Override
    public int compareTo(Record record) {
        return record.getScore()-this.getScore();
    }
}
