package com.mc.kvats.sensodata;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;


    private Sensor GyroscopeSensor;
    private Sensor OrientationSensor;
    private Sensor GPSSensor;
    private Sensor ProximitySensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        MainFragment mainFragment = new MainFragment();
        fragmentTransaction.replace(R.id.fragment_container, mainFragment);
        fragmentTransaction.commit();

//        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//        AccelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//        GyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
//        OrientationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
////        GPSSensor = sensorManager.getDefaultSensor(Sensor.TYPE_)
//        ProximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        sensorManager.registerListener(this, AccelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
//        sensorManager.registerListener(this, GyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);
//        sensorManager.registerListener(this, OrientationSensor, SensorManager.SENSOR_DELAY_NORMAL);
//        sensorManager.registerListener(this, ProximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        sensorManager.unregisterListener(this);
    }
}
