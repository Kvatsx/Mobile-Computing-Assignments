package com.mc.kvats.assignment_2;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class FragmentList extends Fragment {

    static final String TAG = "FragmentList";
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private ListView listView, downloadedMusic;
    private TextView SongName;
    private ImageButton DownloadButton, StopButton, PlayButton;
    private android.support.v4.app.FragmentManager fragmentManager;

    File filesInDirectory[];
    ArrayList<String> songlist;
    final int[] resID = {R.raw.wwesong, R.raw.yara};
    final String[] items = {"WWE Song", "Yara teri yari ko"};

    public FragmentList() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        downloadedMusic = (ListView) rootView.findViewById(R.id.downloadedFiles);

        SongName = (TextView) rootView.findViewById(R.id.songName);
        DownloadButton = (ImageButton) rootView.findViewById(R.id.downloadButton);
        StopButton = (ImageButton) rootView.findViewById(R.id.stopButton);
        PlayButton = (ImageButton) rootView.findViewById(R.id.playButton);

        SongName.setMovementMethod(new ScrollingMovementMethod());

        downloadedMusic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                playDownloadedSong(position);
            }
        });

        DownloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startService(new Intent(getActivity(), NetConnectivity.class));
                updateList();
            }
        });

        StopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().stopService(new Intent(getActivity(), MediaPlayerService.class));
                getActivity().stopService(new Intent(getActivity(), NewMediaPlayerService.class));
                SongName.setText("");
                Log.i(TAG, "Stop Pressed");
                updateList();
            }
        });

        PlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "hey3");
                Intent myIntent = new Intent(getActivity(), MediaPlayerService.class);
                myIntent.putExtra("songId", resID[0]);
                Log.i(TAG, "hey4");
                getActivity().stopService(new Intent(getActivity(), NewMediaPlayerService.class));
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

        updateList();

        return rootView;
    }

    public void playDownloadedSong(int posi) {
        Intent myIntent = new Intent(getActivity(), NewMediaPlayerService.class);
        myIntent.putExtra("songId", songlist.get(posi));
        getActivity().stopService(new Intent(getActivity(), MediaPlayerService.class));
        getActivity().startService(myIntent);
        Log.i(TAG, "hey2D");
        SongName.setText(songlist.get(posi));
    }

    public void playSong(int posi) {
        Log.i(TAG, "hey1");
        Intent myIntent = new Intent(getActivity(), MediaPlayerService.class);
        myIntent.putExtra("songId", resID[posi]);
        getActivity().stopService(new Intent(getActivity(), NewMediaPlayerService.class));
        getActivity().startService(myIntent);
        Log.i(TAG, "hey2");
        SongName.setText(items[posi]);
    }

    public void updateList() {
        Log.i(TAG, "Searching for file");
        String path = Environment.getExternalStorageDirectory().toString()+"/Android/data/com.mc.kvats.assignment_2/files"+ File.separator + Environment.DIRECTORY_MUSIC;


        Log.i(TAG, path);
        File file = new File(path);
        String mRootPath = file.getAbsoluteFile().getPath();


        songlist = new ArrayList<String>();
        ArrayList<String> mFileNames = new ArrayList<String>();

        filesInDirectory = file.listFiles();
        Log.i(TAG, "File found maybe");

        if (filesInDirectory != null) {
            Log.i(TAG, "Length: "+String.valueOf(filesInDirectory.length));
            for (int i = 0; i<filesInDirectory.length;i++) {
                mFileNames.add(filesInDirectory[i].getName());
                Log.i(TAG, filesInDirectory[i].getName());
                songlist.add(mFileNames.get(i));
            }
        }
        Log.i(TAG, "File not found");
        ArrayAdapter<String> items = new ArrayAdapter<String>(getActivity(), R.layout.list_items, mFileNames);
        downloadedMusic.setAdapter(items);
    }
}
