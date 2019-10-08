package com.example.freddy.vehicle_maint;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class MultipleFragment extends Fragment {

    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

    Bitmap[] img1;
    Bitmap[] img2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        StrictMode.setThreadPolicy(policy);

        // Inflate the layout for this fragment
        Log.wtf("Flag1","Flag1");
        RecyclerView Recycler = (RecyclerView)inflater.inflate(
                R.layout.fragment_multiple, container, false);

        URL url = null;
        try {

            url = new URL("http://192.168.43.116:3000/prev");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //httpUrlConnection.setUseCaches(false);
            //httpUrlConnection.setDoOutput(true);

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Cache-Control", "no-cache");
            //connection.setRequestProperty(
            //        "Content-Type", "multipart/form-data;boundary=" + MulitipleActivity.boundary);

            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestMethod("POST");

            StringBuffer response = new StringBuffer();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            Log.wtf("multiplr_log",response.toString());
            JSONObject jsonObject = new JSONObject(response.toString());
            JSONArray data = new JSONArray(jsonObject.getString("answer"));
            img1 = new Bitmap[data.length()];
            img2 = new Bitmap[data.length()];
            for (int i = 0; i < data.length(); i++) {

                JSONObject explrObject = data.getJSONObject(i);

                String image_res = explrObject.getString("img1");
                //byte[] decodedString = Base64.decode(image_res, Base64.DEFAULT);
                //img1[i] = explrObject.getString("img1");
                image_res = explrObject.getString("img2");
                //decodedString = Base64.decode(image_res, Base64.DEFAULT);
                //img2[i] = explrObject.getString("img2");

            }

            Adapter adapter = new Adapter(img1, img2);
            Recycler.setAdapter(adapter);
            GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
            Recycler.setLayoutManager(layoutManager);



            //return pizzaRecycler;
            return Recycler;

        }  catch (Exception e) {
            Log.wtf("error_multiple","error_multiple");
            e.printStackTrace();
            Toast.makeText(getContext(),"Connection Error",Toast.LENGTH_SHORT);
        }
        return Recycler;
    }


}
