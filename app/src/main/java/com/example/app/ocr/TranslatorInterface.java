package com.example.app.ocr;

import java.io.*;
import java.net.*;
import javax.net.ssl.HttpsURLConnection;


/**
 * Created by Jan Helmich on 21.01.2018.
 */

public class TranslatorInterface {
    // Replace the subscriptionKey string value with your valid subscription key.
    static String subscriptionKey = "0b1aa5b034174758953b3a8339643838";

    static String host = "https://api.microsofttranslator.com";
    static String path = "/V2/Http.svc/Translate";

    public static String Translate (String text, String target) throws Exception {

        text = URLEncoder.encode(text, "UTF-8");


        String encoded_query = URLEncoder.encode (text, "UTF-8");
        String params = "?to=" + target + "&text=" + text;
        URL url = new URL (host + path + params);

        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Ocp-Apim-Subscription-Key", subscriptionKey);
        connection.setDoOutput(true);

        StringBuilder response = new StringBuilder ();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        in.close();
        // System.out.println("Reeceived " + response.toString());
        return response.toString();

    }

    public static void main(String[] args) {
        try {
            String response = Translate ("Milch. Hafer. Weizen. Nüsse","en");
            System.out.println (response);
        }
        catch (Exception e) {
            System.out.println (e);
        }
    }
}
