package com.example.freddy.vehicle_maint;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class user extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
    }
    public void logout(View v){
        Intent i = new Intent(getBaseContext(), login.class);
        startActivity(i);
    }
    public void m_check(View v){
        Intent i = new Intent(getBaseContext(), mcheck.class);
        startActivity(i);
    }

    public void visual(View view){

    }

    public void multiple(View view){
        Intent intent =  new Intent(getBaseContext(),MulitipleActivity.class);
        startActivity(intent);
    }

    public void start_vid(View view){
        Intent intent = new Intent(getBaseContext(),VideoActivity.class);
        startActivity(intent);
    }

    public void vid2(View view){
        Intent intent = new Intent(getBaseContext(),Video2Activity.class);
        startActivity(intent);
    }

}
