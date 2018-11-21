package com.mc.kvats.sensodata;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import static android.content.Context.MODE_PRIVATE;

public class MainFragment extends Fragment {
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Button Accelerometer, Gyroscope, Orientation, GPS, Proximity, ShowData;

    private Helper hp = Helper.getInstance();
    private SQLiteDatabase db;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragmentManager = getActivity().getSupportFragmentManager();

        db = getContext().openOrCreateDatabase(hp.DATABASE_NAME, MODE_PRIVATE, null);
//        db.execSQL("CREATE TABLE IF NOT EXISTS "+ hp.TABLE_NAME_1 +"(ID INTEGER PRIMARY KEY AUTOINCREMENT, "+hp.COL_1_1+" TEXT, "+hp.COL_1_2+" TEXT, "+hp.COL_1_3+" TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS Accelerometer(ID INTEGER PRIMARY KEY AUTOINCREMENT, X TEXT, Y TEXT, Z TEXT)");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View RootView = inflater.inflate(R.layout.fragment_main, container, false);

        Accelerometer = (Button) RootView.findViewById(R.id.accelerometer);
        Gyroscope = (Button) RootView.findViewById(R.id.gyroscope);
        Orientation = (Button) RootView.findViewById(R.id.orientation);
        GPS = (Button) RootView.findViewById(R.id.gps);
        Proximity = (Button) RootView.findViewById(R.id.proximity);
        ShowData = (Button) RootView.findViewById(R.id.showData);

        Accelerometer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AccelerometerFragment accelerometerFragment = new AccelerometerFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, accelerometerFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        Gyroscope.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GyroscopeFragment gyroscopeFragment = new GyroscopeFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, gyroscopeFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        Orientation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrientationFragment orientationFragment = new OrientationFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, orientationFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        GPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GPSFragment gpsFragment = new GPSFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, gpsFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        Proximity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProximityFragment proximityFragment = new ProximityFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, proximityFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        ShowData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return RootView;
    }
}
