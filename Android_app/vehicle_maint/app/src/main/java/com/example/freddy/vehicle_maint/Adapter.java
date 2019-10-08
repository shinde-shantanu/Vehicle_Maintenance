package com.example.freddy.vehicle_maint;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;

/**
 * Created by Shantanu Shinde on 02-03-2019.
 */

public class Adapter extends
        RecyclerView.Adapter<Adapter.ViewHolder> {

    private Bitmap[] img1;
    private Bitmap[] img2;
    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //Define the view to be used for each data item
        private CardView cardView;

        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }

    public Adapter(Bitmap[] img1, Bitmap[] img2){
        this.img1 = img1;
        this.img2 = img2;
    }

    @Override
    public int getItemCount(){
        return img1.length;
    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType){
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        CardView cardView = holder.cardView;

        //WeakReference<Activity> mActivityRef;
        //mActivityRef = new WeakReference<Activity>(g);

        //Context context = MulitipleActivity.get_con();
        //Picasso.with(MulitipleActivity.CONTEXT_IGNORE_SECURITY).
                        //load("http://192.168.43.116:8888/image/2019-03-02%2010%3A48%3A29.jpg").into(imageView);

        ImageView imageView1 = (ImageView)cardView.findViewById(R.id.mul_img1);
        imageView1.setImageBitmap(img1[position]);

        ImageView imageView2 = (ImageView)cardView.findViewById(R.id.mul_img2);
        imageView2.setImageBitmap(img2[position]);

    }

}
