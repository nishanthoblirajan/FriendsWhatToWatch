package com.zaptrapp.friendswhattowatch.Utility;

import android.content.Context;
import android.util.DisplayMetrics;

import java.util.Random;

import static android.R.attr.data;

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

    public static String convertTMDBDate(String date){
        if(date!=null) {
            String[] data = date.split("-");
            return data[2] + "-" + data[1] + "-" + data[0];
        }
        return "N/A";

    }
}

