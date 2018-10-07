package com.mc.kvats.quizapp;

import android.app.ProgressDialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.android.internal.http.multipart.MultipartEntity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity.class";
    private static final String FILENAME = "QuestionAnswer.csv";

    private static final int REQUEST_FILE_CODE = 200;
    private static final int READ_REQUEST_CODE = 300;
    private static final String SERVER_PATH = "http://192.168.150.128/assignment3/index2.php";
    private File csv_file;

    private FrameLayout frameLayout;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Button submit_button;

    private Helper hp = Helper.getInstance();
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = openOrCreateDatabase(hp.DATABASE_NAME, MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS QuestionAnswer(ID INTEGER PRIMARY KEY AUTOINCREMENT, Question TEXT, Answer TEXT)");
//        db.execSQL("CREATE TABLE QuestionAnswer(ID INTEGER PRIMARY KEY AUTOINCREMENT, Question TEXT, Answer TEXT)");

        submit_button = (Button) findViewById(R.id.submit_button);

        frameLayout = (FrameLayout) findViewById(R.id.fragment_container);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        MainListFragment mainListFragment = new MainListFragment();
        ft.replace(R.id.fragment_container, mainListFragment);
        ft.commit();
    }

    public void ExportCSV(View view) {
        String query = "SELECT * FROM QuestionAnswer";
        Cursor cursor = db.rawQuery(query, null);
        if ( cursor.getCount() > 0 ) {
            File file = new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), FILENAME);
//            Log.d(TAG, getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).toString());
            try {
                FileWriter fileWriter = new FileWriter(file, false);
                String[] colName = cursor.getColumnNames();
                String out = colName[0] + ", " + colName[1] + ", " + colName[2] +"\n";
                fileWriter.write(out);
                while (cursor.moveToNext()) {
                    out = csvHelper(cursor.getString(0)) + ", " + csvHelper(cursor.getString(1)) + ", " + csvHelper(cursor.getString(2))+"\n";
                    fileWriter.write(out);
                }
                fileWriter.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        csv_file = new File(getExternalFilesDir((Environment.DIRECTORY_DOCUMENTS)), FILENAME);
        Log.d(TAG, csv_file.getName().toString());

        if ( isConnected() ) {
            Toast.makeText(this, "Connected!", Toast.LENGTH_SHORT).show();
            CSVUploadAsyncTask csvUploadAsyncTask = new CSVUploadAsyncTask(this);
            csvUploadAsyncTask.execute();
        }
        else {
            Toast.makeText(this, "Not Connected!", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean isConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Service.CONNECTIVITY_SERVICE);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.execSQL("DROP TABLE QuestionAnswer");
    }

    private static String csvHelper(String str) {
        return "\"" + str + "\"";
    }


    private class CSVUploadAsyncTask extends AsyncTask<Void, Integer, String> {

        HttpClient httpClient = new DefaultHttpClient();
        private Context context;
        private ProgressDialog progressDialog;

        private CSVUploadAsyncTask(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(Void... voids) {

            HttpResponse httpResponse = null;
            HttpEntity httpEntity = null;
            String responseString = null;

            try {
                HttpPost httpPost = new HttpPost(SERVER_PATH);
                MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
                multipartEntityBuilder.addPart("csv_file", new FileBody(csv_file));
                MyHttpEntity.ProgressListener progressListener = new MyHttpEntity.ProgressListener() {
                    @Override
                    public void transferred(float progress) {
                        publishProgress((int) progress);
                    }
                };
                httpPost.setEntity(new MyHttpEntity(multipartEntityBuilder.build(), progressListener));
                httpResponse = httpClient.execute(httpPost);
                httpEntity = httpResponse.getEntity();
                int statusCode = httpResponse.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    // Server response
                    responseString = EntityUtils.toString(httpEntity);
                    Toast.makeText(context, "File Uploaded", Toast.LENGTH_SHORT).show();
                }
                else {
                    responseString = "Error occurred! Http Status Code: " + statusCode;
                }

            }
            catch (UnsupportedEncodingException | ClientProtocolException e) {
                e.printStackTrace();
                Log.d(TAG, e.getMessage());
            }
            catch (IOException e) {
                e.printStackTrace();
                Log.d(TAG, e.getMessage());
            }
            Log.d(TAG, "Message: "+responseString);
            return responseString;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            this.progressDialog.setProgress((int) values[0]);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.progressDialog = new ProgressDialog(this.context);
            this.progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            this.progressDialog.setCancelable(false);
            this.progressDialog.show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            this.progressDialog.dismiss();
            progressDialog = null;
            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
        }
    }

}
