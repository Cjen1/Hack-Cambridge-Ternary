package com.example.app.ocr;// This sample uses the Apache HTTP client library(org.apache.httpcomponents:httpclient:4.2.4)
// and the org.json library (org.json:json:20170516).

import android.net.Uri;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;


import org.json.JSONObject;

public class OCRInterface {
    // **********************************************
    // *** Update or verify the following values. ***
    // **********************************************

    // Replace the subscriptionKey string value with your valid subscription key.
    public static final String subscriptionKey = "394544b7717d43099f191114fec25c09";

    // Replace or verify the region.
    //
    // You must use the same region in your REST API call as you used to obtain your subscription keys.
    // For example, if you obtained your subscription keys from the westus region, replace
    // "westcentralus" in the URI below with "westus".
    //
    // NOTE: Free trial subscription keys are generated in the westcentralus region, so if you are using
    // a free trial subscription key, you should not need to change this region.
    //
    // Also, if you want to use the celebrities model, change "landmarks" to "celebrities" here and in
    // uriBuilder.setParameter to use the Celebrities model.
    public static final String uriBase = "https://westcentralus.api.cognitive.microsoft.com/vision/v1.0/ocr";


    public static List<String> getText(JSONObject o) {
        System.out.println(o.toString());
        List<String> l = new LinkedList<>();
        String s = o.toString();
        int pos;
        int end;
        while (s.length() > 0) {
            pos = s.indexOf("text");

            if (pos >= 0) {
                end = s.indexOf('"', pos + 7);
                String word = s.substring(pos + 7, end);
                s = s.substring(end);
                l.add(word);
                // TODO: System.out.println(word);
            } else {
                break;
            }


        }


        return l;
    }


    public static JSONObject analyseLocal(String imgUrl) {
        File f = new File(imgUrl);
        JSONObject result = serverRequest(f, "application/octet-stream");
        return result;
    }

    public static List<String> analyseLocalToText(String imgUrl) {
        File f = new File(imgUrl);
        JSONObject result = serverRequest(f, "application/octet-stream");
        List<String> l = getText(result);

        return l;
    }

    /*
    public static JSONObject analyseURL(String url) {
        try {
            String s = "{\"url\":\"" + url + "\"}";
            File f = URL
            JSONObject result = serverRequest(, "application/json");
            return result;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    */

    private static JSONObject serverRequest(File f, String contentType) {
        try {
            // NOTE: You must use the same location in your REST call as you used to obtain your subscription keys.
            //   For example, if you obtained your subscription keys from westus, replace "westcentralus" in the
            //   URL below with "westus".
            Uri.Builder builder = new Uri.Builder();
            builder.appendQueryParameter("language", "unk");
            builder.appendQueryParameter("detectOrientation", "true");

            URL url = new URL(uriBase + "?language=unk&detectOrientation=true");

            HttpURLConnection cn = (HttpURLConnection) url.openConnection();
            cn.setDoOutput(true);
            cn.setRequestProperty("contentType", "application/octet-stream");
            OutputStream os = cn.getOutputStream();
            byte[] b = new byte[100];

            FileInputStream fi = new FileInputStream(f);
            int r;
            while ((r = fi.read(b)) >= 0) {
                os.write(b, 0, r);
            }
            DataInputStream is = new DataInputStream(cn.getInputStream());
            String jsonString = "";
            try {
                while (true) {
                    jsonString += is.readUTF();
                }

            } catch (EOFException e) {
                is.close();
            }

            cn.disconnect();

            JSONObject json = new JSONObject(jsonString);
            //System.out.println("REST Response:\n");
            //System.out.println(json.toString(2));
            return json;

        } catch (Exception e) {
            // Display error message.
            System.out.println(e.getMessage());
            return null;
        }


    }

}