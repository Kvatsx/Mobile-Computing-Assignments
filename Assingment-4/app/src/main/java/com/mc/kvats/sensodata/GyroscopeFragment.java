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

public class GyroscopeFragment extends Fragment implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor GyroscopeSensor;
    private Button Start, Stop;
    private TextView XAxis, YAxis, ZAxis;

    private Boolean StoreData;

    public GyroscopeFragment() {
        // Required empty public constructor
        this.StoreData = false;
    }

    public static GyroscopeFragment newInstance(String param1, String param2) {
        GyroscopeFragment fragment = new GyroscopeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sensorManager = (SensorManager) getActivity().getSystemService(getContext().SENSOR_SERVICE);
        if ( sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) != null ) {
            GyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        }
        else {
            Toast.makeText(getContext(), "This Device doesn't have an Gyroscope Sensor!", Toast.LENGTH_SHORT).show();
            getActivity().onBackPressed();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View RootView = inflater.inflate(R.layout.fragment_gyroscope, container, false);
        Start = (Button) RootView.findViewById(R.id.start);
        Stop = (Button) RootView.findViewById(R.id.stop);
        XAxis = (TextView) RootView.findViewById(R.id.xAxis);
        YAxis = (TextView) RootView.findViewById(R.id.yAxis);
        ZAxis = (TextView) RootView.findViewById(R.id.zAxis);

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
        XAxis.setText(Float.toString(sensorEvent.values[0]));
        YAxis.setText(Float.toString(sensorEvent.values[1]));
        ZAxis.setText(Float.toString(sensorEvent.values[2]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, GyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
