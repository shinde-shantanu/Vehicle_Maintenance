package com.example.freddy.vehicle_maint;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        ImageView imageView = (ImageView) findViewById(R.id.show);
        Intent intent = getIntent();
        byte[] decodedString = intent.getByteArrayExtra("byte");
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        imageView.setImageBitmap(decodedByte);
        //String per = intent.getStringExtra("per");
        //TextView textView = (TextView) findViewById(R.id.per);
        //textView.setText(per);
    }
}
