package com.example.astroidfield;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

public class MySensor {

    private SensorManager sensorManager;
    private Sensor accSensor;
    private SensorEventListener accSensorEventListener  = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            callBack_sensors.getData(x,y);
        }
        @Override
        public void onAccuracyChanged(android.hardware.Sensor sensor, int i) {}
    };;

    public MySensor() { }

    public CallBack_Sensors getCallBack_sensors() {
        return callBack_sensors;
    }

    public void setCallBack_sensors(CallBack_Sensors callBack_sensors) {
        this.callBack_sensors = callBack_sensors;
    }

    private CallBack_Sensors callBack_sensors;




    public void initSensor(Context context) {
        sensorManager = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
        accSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    public void pauseSensor(){
        sensorManager.unregisterListener(accSensorEventListener);

    }

    public void resumeSensor(){
        sensorManager.registerListener(accSensorEventListener, accSensor, SensorManager.SENSOR_DELAY_UI);

    }

}
