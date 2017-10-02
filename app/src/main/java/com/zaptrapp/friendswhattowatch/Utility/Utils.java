package com.zaptrapp.friendswhattowatch.Utility;

import android.content.Context;
import android.util.DisplayMetrics;

import java.util.Random;

/**
 * Created by Nishanth on 02-Oct-17.
 */

public class Utils {

    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / 110);
        return noOfColumns;
    }

    public static int randomNumber(int min, int max) {

        Random r = new Random();
        return r.nextInt(max - min + 1) + min;
    }
}

