package com.example.freddy.vehicle_maint;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.StrictMode;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class uploadpic extends AppCompatActivity {

    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

    public String attachmentName = "bitmap";
    public String attachmentFileName = "bitmap.bmp";
    public String crlf = "\r\n";
    public String twoHyphens = "--";
    public String boundary =  "*****";
    private ImageView photo = null;
    public Bitmap bitmap = null;
    String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        StrictMode.setThreadPolicy(policy);

        //ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadpic);
        Bundle extras = getIntent().getExtras();
        value = extras.getString("key");
        TextView view = (TextView) findViewById(R.id.textView7);
        view.setText(value);
        Button btn = (Button) findViewById(R.id.submit);
        btn.setEnabled(false);
    }

    public void back1(View v) {
        Intent i = new Intent(getBaseContext(), mcheck.class);
        startActivity(i);
    }

    public static final int GET_FROM_GALLERY = 3;

    public void uploadimage(View v) {

        startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        photo = (ImageView) findViewById(R.id.uploadimage);

        //Detects request codes
        if (requestCode == GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                photo.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

    public void verify(View v) {

        ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Checking the image");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        //progress.setProgress(0);
        progress.show();
        //Button v_button = (Button) findViewById(R.id.verify);
        //v_button.setVisibility(View.GONE);
        //progress.create();
        URL url = null;
        try {
            url = new URL("http://192.168.43.116:3000");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //httpUrlConnection.setUseCaches(false);
            //httpUrlConnection.setDoOutput(true);

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Cache-Control", "no-cache");
            connection.setRequestProperty(
                    "Content-Type", "multipart/form-data;boundary=" + this.boundary);

            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestMethod("POST");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
            byte[] pixels = baos.toByteArray();
            String encodedImage = Base64.encodeToString(pixels, Base64.DEFAULT);

            JSONObject object = new JSONObject();
            object.put("image", encodedImage);
            object.put("part", value);

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

        }  catch (Exception e) {
            Log.wtf("error_verify","error_verify");
            e.printStackTrace();
            progress.dismiss();
            Toast.makeText(this,"Error Connecting",Toast.LENGTH_LONG).show();
            //progress.dismiss();
        }
    }

    public void submit(View view){


        ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Analysing the image");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.show();
        URL url = null;
        try {

            url = new URL("http://192.168.43.116:3000/abc");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //httpUrlConnection.setUseCaches(false);
            //httpUrlConnection.setDoOutput(true);

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Cache-Control", "no-cache");
            connection.setRequestProperty(
                    "Content-Type", "multipart/form-data;boundary=" + this.boundary);

            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestMethod("POST");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] pixels = baos.toByteArray();
            String encodedImage = Base64.encodeToString(pixels, Base64.DEFAULT);

            JSONObject object = new JSONObject();
            object.put("image", encodedImage);
            object.put("part", value);

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

            JSONObject data = new JSONObject(response.toString());
            String image_res = data.getString("image");
            byte[] decodedString = Base64.decode(image_res, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            //ImageView imageView = (ImageView) findViewById(R.id.res_img);
            //imageView.setImageBitmap(decodedByte);
            photo.setImageBitmap(decodedByte);
            progress.dismiss();
            Log.wtf("Answer",response.toString());

        }  catch (Exception e) {
            Log.wtf("error","error");
            e.printStackTrace();
            progress.dismiss();
            Toast.makeText(this,"Connection Error",Toast.LENGTH_SHORT);
        }

    }

}


            /*DataOutputStream request = new DataOutputStream(
                    httpUrlConnection.getOutputStream());

            request.writeBytes(this.twoHyphens + this.boundary + this.crlf);
            request.writeBytes("Content-Disposition: form-data; name=\"" +
                    this.attachmentName + "\";filename=\"" +
                    this.attachmentFileName + "\"" + this.crlf);
            request.writeBytes(this.crlf);

            byte[] pixels = new byte[bitmap.getWidth() * bitmap.getHeight()];
            for (int i = 0; i < bitmap.getWidth(); ++i) {
                for (int j = 0; j < bitmap.getHeight(); ++j) {
                    //we're interested only in the MSB of the first byte,
                    //since the other 3 bytes are identical for B&W images
                    pixels[i + j] = (byte) ((bitmap.getPixel(i, j) & 0x80) >> 7);
                }
            }
            request.write(pixels);

            request.writeBytes(this.crlf);
            request.writeBytes(this.twoHyphens + this.boundary +
                    this.twoHyphens + this.crlf);

            request.flush();
            request.close();


            <ProgressBar
        android:id="@+id/progressBar"
        style="?android:attr/progressBarStyle"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginBottom="8dp"
        android:layout_marginEnd="8dp"
        android:layout_marginStart="8dp"
        android:layout_marginTop="8dp"
        android:animationResolution="500"
        android:indeterminate="true"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />*/