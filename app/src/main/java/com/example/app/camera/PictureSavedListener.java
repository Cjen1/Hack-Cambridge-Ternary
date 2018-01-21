package com.example.app.camera;

import android.util.Log;

import com.example.app.DataCentre;
import com.example.app.allergic.EatEvent;
import com.example.app.ocr.OCRInterface;

import java.util.List;

/**
 * Created by user on 20/01/2018.
 */

public class PictureSavedListener {
    private static final String TAG = "OCR saveListener";

    public void notifyOCR(String filepath){
        List<String> textTokens = OCRInterface.analyseLocalToText(filepath);
        EatEvent e = new EatEvent(System.currentTimeMillis(), textTokens);
        DataCentre.addEatEvent(e);
        Log.i(TAG, "notifyOCR: Made it to the OCR notifier");
    }
}
