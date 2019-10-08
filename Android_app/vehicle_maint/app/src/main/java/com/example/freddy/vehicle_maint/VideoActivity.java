package com.example.freddy.vehicle_maint;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.camerakit.CameraKitView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;


public class VideoActivity extends AppCompatActivity {

    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

    private CameraKitView cameraKitView;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        cameraKitView = findViewById(R.id.camera);
        StrictMode.setThreadPolicy(policy);
        intent = new Intent(getBaseContext(),DisplayActivity.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        cameraKitView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cameraKitView.onResume();
    }

    @Override
    protected void onPause() {
        cameraKitView.onPause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        cameraKitView.onStop();
        super.onStop();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        cameraKitView.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /*public static int i=1;
    public void click1(View view){
        final Button button = (Button)findViewById(R.id.click2);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                button.performClick();
            }
        }, 4000);
    }*/
    int f = 0;
    /*public void click2(View v) {

        final Button btn = (Button)findViewById(R.id.click1);
        cameraKitView.captureImage(new CameraKitView.ImageCallback() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onImage(CameraKitView cameraKitView, final byte[] capturedImage) {
                //File savedPhoto = new File(Environment.getExternalStorageDirectory(), "photo"+f+".jpg");
                URL url = null;
                try {
                    //String encodedImage = Base64.getEncoder().encodeToString(capturedImage);
                    url = new URL("http://192.168.43.116:3000/abc1");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    //httpUrlConnection.setUseCaches(false);
                    //httpUrlConnection.setDoOutput(true);

                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Connection", "Keep-Alive");
                    connection.setRequestProperty("Cache-Control", "no-cache");
                    ///connection.setRequestProperty(
                            //"Content-Type", "multipart/form-data;boundary=" + VideoActivity.this.boundary);

                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                    connection.setRequestProperty("Accept", "application/json");
                    connection.setRequestMethod("POST");

                    JSONObject object = new JSONObject();
                    object.put("image", capturedImage);
                    Log.wtf("log",capturedImage.toString());

                    OutputStream os = connection.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(os, "UTF-8"));
                    writer.write(object.toString());
                    writer.flush();
                    writer.close();
                    os.close();

                    connection.connect();

                    StringBuffer response = new StringBuffer();
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));
                    String inputLine;

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    Log.wtf("Answer",response.toString());

                    if(response.toString().length()>0){
                        JSONObject jsonObject = new JSONObject(response.toString());
                        if (response.toString().equals("yes")){
                            Toast.makeText(this,value+" found. Please click submit button",Toast.LENGTH_LONG).show();
                            Button btn = (Button) findViewById(R.id.submit);
                            btn.setEnabled(true);
                            btn.setBackgroundColor(getResources().getColor(R.color.grren));
                            progress.dismiss();
                        }
                        else {
                            progress.dismiss();
                            Toast toast = Toast.makeText(this,value+" not found",Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }


                }  catch (Exception e) {
                    Log.w("Error",e.toString());
                    Toast.makeText(VideoActivity.this,"Error Connecting",Toast.LENGTH_LONG).show();
                    //progress.dismiss();
                }
            }
        });
        btn.performClick();
        f++;
    }*/

    public void click1(View v) {

        cameraKitView.captureImage(new CameraKitView.ImageCallback() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onImage(CameraKitView cameraKitView, final byte[] capturedImage) {
                //File savedPhoto = new File(Environment.getExternalStorageDirectory(), "photo"+f+".jpg");

                URL url = null;
                /*try {
                    String encodedImage = Base64.getEncoder().encodeToString(capturedImage);
                    url = new URL("http://192.168.43.116:5000/");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    //httpUrlConnection.setUseCaches(false);
                    //httpUrlConnection.setDoOutput(true);

                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Connection", "Keep-Alive");
                    connection.setRequestProperty("Cache-Control", "no-cache");
                    //connection.setRequestProperty(
                    //        "Content-Type", "multipart/form-data;boundary=" + VideoActivity.boundary);

                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                    connection.setRequestProperty("Accept", "application/json");
                    connection.setRequestMethod("POST");

                    JSONObject object = new JSONObject();
                    object.put("file", encodedImage);

                    OutputStream os = connection.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(os, "UTF-8"));
                    writer.write(object.toString());
                    writer.flush();
                    writer.close();
                    os.close();

                    connection.connect();

                    StringBuffer response = new StringBuffer();
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));
                    String inputLine;

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    Log.wtf("Answer",response.toString());
                    JSONObject data = new JSONObject(response.toString());
                    String decodedString = data.getString("ans");
                    intent.putExtra("per",decodedString);
                    Log.wtf("log_venn",capturedImage.toString());
                }  catch (Exception e) {
                    Log.w("Error_venn",e.toString());
                    Toast.makeText(VideoActivity.this,"Error Connecting",Toast.LENGTH_LONG).show();
                    //progress.dismiss();
                }*/
                //172.16.0.202:5000/upload
                url = null;
                try {
                    String encodedImage = Base64.getEncoder().encodeToString(capturedImage);
                    url = new URL("http://192.168.43.116:3000/abc1");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    //httpUrlConnection.setUseCaches(false);
                    //httpUrlConnection.setDoOutput(true);

                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Connection", "Keep-Alive");
                    connection.setRequestProperty("Cache-Control", "no-cache");
                    ///connection.setRequestProperty(
                    //"Content-Type", "multipart/form-data;boundary=" + VideoActivity.this.boundary);

                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                    connection.setRequestProperty("Accept", "application/json");
                    connection.setRequestMethod("POST");

                    JSONObject object = new JSONObject();
                    object.put("image", encodedImage);
                    Log.wtf("log",capturedImage.toString());

                    OutputStream os = connection.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(os, "UTF-8"));
                    writer.write(object.toString());
                    writer.flush();
                    writer.close();
                    os.close();

                    connection.connect();

                    StringBuffer response = new StringBuffer();
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));
                    String inputLine;

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    //Log.wtf("Answer",response.toString());
                    JSONObject data = new JSONObject(response.toString());
                    String image_res = data.getString("image");
                    byte[] decodedString = android.util.Base64.decode(image_res, android.util.Base64.DEFAULT);
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    //Intent intent = new Intent(getBaseContext(),DisplayActivity.class);
                    intent.putExtra("byte",decodedString);
                    //intent.putExtra("b64",image_res);
                    startActivity(intent);

                }  catch (Exception e) {
                    Log.w("Error",e.toString());
                    Toast.makeText(VideoActivity.this,"Error Connecting",Toast.LENGTH_LONG).show();
                    //progress.dismiss();
                }
            }
        });
    }

}


