package com.example.app;

import com.example.app.allergyHandling.AllergeneHandler;
import com.example.app.ocr.OCRInterface;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by Jan Helmich on 20.01.2018.
 */

public class Main {

    public static void main(String[] args) {
        AllergeneHandler ah = new AllergeneHandler();
        JSONObject o = OCRInterface.analyseLocal("app/res/allergyInfo/test1.jpg");

        List<String> tokens = OCRInterface.getText(o);
        for (String token : tokens) {
            System.out.println(token);
        }

        List<String> found = ah.allergicIngredients(tokens);
        for (String s : found) {
            System.out.println("ALERT: " + s + " found in the food.");
        }

        /*
        o = OCRInterface.analyseURL("http://4.bp.blogspot.com/-OI60LUigHek/Udzb-6fgmzI/AAAAAAAAACE/GiK0zWgOgqo/s1600/cheetos-nutrition.JPG");

        tokens = OCRInterface.getText(o);
        for (String token : tokens) {
            System.out.println(token);
        }

        found = ah.allergicIngredients(tokens);
        for (String s : found) {
            System.out.println("ALERT: " + s + " found in the food.");
        }*/
    }

}
