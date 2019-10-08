package com.example.freddy.vehicle_maint;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View v){
        EditText username =(EditText)findViewById(R.id.username);
        String uname = username.getText().toString();
        EditText password =(EditText)findViewById(R.id.password);
        String pass = password.getText().toString();
        String t = "";
        String p = "";

        Intent i = new Intent(this, user.class);

        if (uname.equals(t)&&pass.equals(p)) {
            startActivity(i);
        }
    }
}
