package com.mc.kvats.assignment_2;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.nfc.Tag;
import android.os.IBinder;
import android.util.Log;

public class MediaPlayerService extends Service {
    final String TAG = "MediaPlayerService";
    MediaPlayer mediaPlayer;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "Service called");
        int songid = Integer.valueOf(intent.getIntExtra("songId", 0));
        Log.i(TAG, "all fine");
        mediaPlayer.reset();
        mediaPlayer = MediaPlayer.create(this, R.raw.wwesong);
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
