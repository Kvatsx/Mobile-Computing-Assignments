package com.mc.kvats.assignment_2;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import java.io.File;

public class NewMediaPlayerService extends Service {

    final String TAG = "MediaPlayerService";
    MediaPlayer mediaPlayer;
    public NewMediaPlayerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "Service called");
        String songId = intent.getStringExtra("songId");
        Log.i(TAG, "all fine");
        mediaPlayer.reset();
        mediaPlayer = MediaPlayer.create(this, Uri.parse(Environment.getExternalStorageDirectory().toString()+"/Android/data/com.mc.kvats.assignment_2/files"+ File.separator + Environment.DIRECTORY_MUSIC + File.separator + songId));
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
        return START_STICKY;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        mediaPlayer = new MediaPlayer();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();
    }
}
