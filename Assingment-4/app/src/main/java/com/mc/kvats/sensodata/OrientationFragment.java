package com.mc.kvats.sensodata;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class OrientationFragment extends Fragment implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor OrientationSensor;

    private Button Start, Stop;
    private TextView Azimuth, Pitch, Roll;
    private float azimith, pitch, roll;
    private Boolean StoreData;

    public OrientationFragment() {
        // Required empty public constructor
        this.StoreData = false;
        this.azimith = 0;
        this.pitch = 0;
        this.roll = 0;
    }

    public static OrientationFragment newInstance() {
        OrientationFragment fragment = new OrientationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sensorManager = (SensorManager) getActivity().getSystemService(getContext().SENSOR_SERVICE);
        if ( sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION) != null ) {
            OrientationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        }
        else {
            Toast.makeText(getContext(), "This Device doesn't have an Orientation Sensor!", Toast.LENGTH_SHORT).show();
            getActivity().onBackPressed();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View RootView = inflater.inflate(R.layout.fragment_orientation, container, false);
        Start = (Button) RootView.findViewById(R.id.start);
        Stop = (Button) RootView.findViewById(R.id.stop);
        Azimuth = (TextView) RootView.findViewById(R.id.azimuth);
        Pitch = (TextView) RootView.findViewById(R.id.pitch);
        Roll = (TextView) RootView.findViewById(R.id.roll);

        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StoreData = true;
            }
        });
        Stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StoreData = false;
            }
        });

        return RootView;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        azimith = sensorEvent.values[0];
        pitch = sensorEvent.values[1];
        roll = sensorEvent.values[2];
        updateOrientation();
    }

    private void updateOrientation() {
        Azimuth.setText(Float.toString(azimith));
        Pitch.setText(Float.toString(pitch));
        Roll.setText(Float.toString(roll));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, OrientationSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
