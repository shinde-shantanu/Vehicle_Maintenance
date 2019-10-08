package com.example.freddy.vehicle_maint;

import android.graphics.Bitmap;

/**
 * Created by Shantanu Shinde on 02-03-2019.
 */

public class multiple_class {
    Bitmap img1;
    Bitmap img2;
    private multiple_class(Bitmap img1, Bitmap img2) {
        this.img1 = img1;
        this.img2 = img2;
    }

    public Bitmap getimg1() {
        return img1;
    }

    public Bitmap getimg2() {
        return img2;
    }

}
