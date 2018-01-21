package com.example.app.camera;

import android.util.Log;

/**
 * Created by user on 20/01/2018.
 */

public class PictureSavedListener {
    private static final String TAG = "OCR Listener";

    public void notifyOCR(String filepath){
        Log.i(TAG, "notifyOCR: Made it to the OCR notifier");
    }
}
