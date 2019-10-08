package com.example.freddy.vehicle_maint;

import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;

public class MulitipleActivity extends FragmentActivity {

    private static final int CONTENT_VIEW_ID = 10101010;

    //public static Context get_con(){
      //  Context context = this.getBaseContext();
      //  return context;
    //}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mulitiple);

        //FrameLayout frame = (FrameLayout) findViewById(R.id.frame);
        //FrameLayout frame = new FrameLayout(this,R.id.frame);
        //frame.setId(CONTENT_VIEW_ID);
        //setContentView(frame, new FrameLayout.LayoutParams(
        //        FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));

        //if (savedInstanceState == null) {
        //    Fragment newFragment = new MultipleFragment();
            //FragmentTransaction ft = getFragmentManager().beginTransaction();
            //ft.add(CONTENT_VIEW_ID, newFragment).commit();
        //}

    }

    /*public static class MultipleFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            RecyclerView Recycler = (RecyclerView)inflater.inflate(
                    R.layout.fragment_multiple, container, false);



            Bitmap[] img1 = new Bitmap[];

            String[] pizzaNames = new String[Pizza.pizzas.length];
            for (int i = 0; i < pizzaNames.length; i++) {
                pizzaNames[i] = Pizza.pizzas[i].getName();
            }

            int[] pizzaImages = new int[Pizza.pizzas.length];
            for (int i = 0; i < pizzaImages.length; i++) {
                pizzaImages[i] = Pizza.pizzas[i].getImageResourceId();
            }

            CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(pizzaNames, pizzaImages);
            pizzaRecycler.setAdapter(adapter);
            return pizzaRecycler;

        }
    }*/

}
