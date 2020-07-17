package com.mc.kvats.assignment_2;

import android.app.DownloadManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import android.widget.Toast;

import java.io.File;

public class NetConnectivity extends Service {

    Context context = this;

    public NetConnectivity() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if ( isConnected() ) {
            Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();
            String link = "http://faculty.iiitd.ac.in/~mukulika/s1.mp3";
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(link));
            String FileName = URLUtil.guessFileName(link, null, MimeTypeMap.getFileExtensionFromUrl(link));
            request.setTitle(FileName);
            request.setDescription("File is being downloading...");

            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_ONLY_COMPLETION);
            request.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_MUSIC, FileName);
            DownloadManager manager = (DownloadManager) getSystemService(context.DOWNLOAD_SERVICE);
            manager.enqueue(request);
            Toast.makeText(this, "File Downloaded", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Not connected", Toast.LENGTH_SHORT).show();
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public boolean isConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Service.CONNECTIVITY_SERVICE);
        if ( connectivityManager != null ) {
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            if ( info != null ) {
                if ( info.getState() == NetworkInfo.State.CONNECTED ) {
                    return true;
                }
            }
        }
        return false;
    }
}
