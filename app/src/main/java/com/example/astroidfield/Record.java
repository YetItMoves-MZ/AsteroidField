package com.example.astroidfield;

public class Record {
    private int odometer = -1;
    private int score = -1;
    private double lat = 0.0;
    private double lon = 0.0;

    public Record() {}

    public long getOdometer() {
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
}
