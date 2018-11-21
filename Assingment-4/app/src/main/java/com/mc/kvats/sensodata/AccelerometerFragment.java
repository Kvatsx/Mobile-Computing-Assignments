package com.mc.kvats.sensodata;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class AccelerometerFragment extends Fragment implements SensorEventListener {
    private static final String TAG = "AccelerometerFragment";
    private SensorManager sensorManager;
    private Sensor AccelerometerSensor;
    private Button Start, Stop;
    private TextView XAxis, YAxis, ZAxis;

    private float xAcc, yAcc, zAcc, xLast, yLast, zLast;
    private Boolean StoreData;

    public AccelerometerFragment() {
        // Required empty public constructor
        this.StoreData = false;
        this.xAcc = 0;
        this.yAcc = 0;
        this.zAcc = 0;
    }

    public static AccelerometerFragment newInstance() {
        AccelerometerFragment fragment = new AccelerometerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sensorManager = (SensorManager) getActivity().getSystemService(getContext().SENSOR_SERVICE);
        if ( sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null ) {
            AccelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }
        else {
            Toast.makeText(getContext(), "This Device doesn't have an Accelerometer Sensor!", Toast.LENGTH_SHORT).show();
            getActivity().onBackPressed();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View RootView = inflater.inflate(R.layout.fragment_accelerometer, container, false);
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
        clearTextViews();
        updateAccelerations();
        xAcc = Math.abs(xLast - sensorEvent.values[0]);
        yAcc = Math.abs(yLast - sensorEvent.values[1]);
        zAcc = Math.abs((zLast - sensorEvent.values[2]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    private void clearTextViews() {
        XAxis.setText("0.0");
        YAxis.setText("0.0");
        ZAxis.setText("0.0");
    }

    private void updateAccelerations() {
        XAxis.setText(Float.toString(xAcc));
        YAxis.setText(Float.toString(yAcc));
        ZAxis.setText(Float.toString(zAcc));
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, AccelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
