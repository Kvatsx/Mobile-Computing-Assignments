package com.mc.kvats.assignment_2;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MediaPlayerService extends Service {

    MediaPlayer mediaPlayer;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int songid = intent.getIntExtra("songId", 0);
        mediaPlayer.reset();
        mediaPlayer = MediaPlayer.create(this, songid);
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

    public void playSong(int code) {
        mediaPlayer.reset();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), code);
        mediaPlayer.start();
    }
}
