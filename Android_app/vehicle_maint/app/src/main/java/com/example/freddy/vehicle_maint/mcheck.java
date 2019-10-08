package com.example.freddy.vehicle_maint;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;

public class mcheck extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mcheck);
    }

    String value;
    public void ontypeclicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.wheels:
                if (checked)
                    value = "Wheels";
                    break;
            case R.id.bonnet:
                if (checked)
                    value = "Bonnet";
                    break;
            case R.id.trunk:
                if (checked)
                    value = "Trunk";
                    break;
            case R.id.sideview:
                if (checked)
                    value = "Side View";
                break;
        }
        Intent i = new Intent(getBaseContext(), uploadpic.class);
        i.putExtra("key",value);
        startActivity(i);
    }
    public void back2(View v) {
        Intent i = new Intent(getBaseContext(), user.class);
        startActivity(i);
    }

    public void funct(View view){

        /*try {
            InputStream is = (InputStream) new URL("http://192.168.43.116:8888/image/2019-03-02%2010%3A48%3A29.jpg").getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            ImageView imageView = (ImageView) findViewById(R.id.temp);
            imageView.setImageDrawable(d);
        } catch (Exception e) {
            //return null;
            Log.wtf("lol","lol");
        }*/

        //2019-03-02 15:21:22.jpg
        Log.wtf("lol","lol");
        //ImageView imageView = (ImageView) findViewById(R.id.temp);
        //Picasso.with(this).
          //      load("http://192.168.43.116:8888/image/2019-03-02%2010%3A48%3A29.jpg").into(imageView);
    }

}
