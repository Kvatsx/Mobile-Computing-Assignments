package com.mc.kvats.assignment_2;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class FragmentList extends Fragment {

    static final String TAG = "FragmentList";
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private ListView listView;
    private TextView SongName;
    private ImageButton DownloadButton, StopButton, PlayButton;
    private android.support.v4.app.FragmentManager fragmentManager;

    final int[] resID = {R.raw.attention, R.raw.mercy , R.raw.thepunisheropeningthemesong, R.raw.yara, R.raw.down, R.raw.ennasona, R.raw.wwesong};
    final String[] items = {"Attention", "Mercy", "Punisher Theme song", "Yara teri yari ko", "Down Jason", "Enna Sona", "WWE Song"};

    public FragmentList() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        SongName = (TextView) rootView.findViewById(R.id.songName);
        DownloadButton = (ImageButton) rootView.findViewById(R.id.downloadButton);
        StopButton = (ImageButton) rootView.findViewById(R.id.stopButton);
        PlayButton = (ImageButton) rootView.findViewById(R.id.playButton);

        SongName.setMovementMethod(new ScrollingMovementMethod());

        StopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().stopService(new Intent(getActivity(), MediaPlayerService.class));
                SongName.setText("");
            }
        });

        PlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "hey3");
                Intent myIntent = new Intent(getActivity(), MediaPlayerService.class);
                myIntent.putExtra("songId", resID[0]);
                Log.i(TAG, "hey4");
                getActivity().startService(myIntent);
                Log.i(TAG, "hey5");
                SongName.setText(items[0]);
            }
        });


        listView = (ListView) rootView.findViewById(R.id.listView);
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_items, items);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                playSong(position);
            }
        });

        return rootView;
    }

    public void playSong(int posi) {
        Log.i(TAG, "hey1");
        Intent myIntent = new Intent(getActivity(), MediaPlayerService.class);
        myIntent.putExtra("songId", resID[posi]);
        getActivity().startService(myIntent);
        Log.i(TAG, "hey2");
        SongName.setText(items[posi]);
    }

}
