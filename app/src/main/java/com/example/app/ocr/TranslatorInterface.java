package com.example.app.ocr;

import org.json.JSONObject;

import java.io.*;
import java.net.*;
import java.util.LinkedList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;


/**
 * Created by Jan Helmich on 21.01.2018.
 */

public class TranslatorInterface {
    // Replace the subscriptionKey string value with your valid subscription key.
    static String subscriptionKey = "0b1aa5b034174758953b3a8339643838";

    static String host = "https://api.microsofttranslator.com";

    private static String targetLanguage = "en"; // By default, target language is set to english

    public static void changeLanguage(String language) {
        if (((List<String>) getLanguages()).contains(language)) { // If language is supported
            targetLanguage = language;
        }
    }

    // TODO: The getLanguage method is nor returning anything. Not even an error!
    public static List<String> getLanguages() {
        try {
            URL url = new URL(host + "/V2/Http.svc/GetLanguagesForTranslate");

            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Ocp-Apim-Subscription-Key", subscriptionKey);
            connection.setDoOutput(true);

            StringBuilder response = new StringBuilder();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();

            List<String> l = new LinkedList<>();
            String r = response.toString();
            while (r.contains("<string>")) {
                int pos = r.indexOf("<string>");
                int posEnd = r.indexOf("</string>", pos);
                String code = r.substring(pos + 8, posEnd);
                l.add(code);
                r = r.substring(pos + 8);
            }
            return l;
        } catch (ProtocolException e) {
            e.printStackTrace();
            return null;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String identify(String text) throws Exception {

        // Required to make it HTML uniform for the REST API
        String encodedText = URLEncoder.encode(text, "UTF-8");

        String params = "&text=" + encodedText;
        URL url = new URL(host + "/V2/Http.svc/Detect" + params);

        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Ocp-Apim-Subscription-Key", subscriptionKey);
        connection.setDoOutput(true);
        System.out.println("So far, so good");
        StringBuilder response = new StringBuilder();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
            response.append(line);
        }
        in.close();
        return response.toString();

    }

    public static String translate(String text) {
        return translate(text,targetLanguage);
    }

    public static String translate(String text, String target) {
        try {
            // Required to make it HTML uniform for the REST API
            String encodedText = URLEncoder.encode(text, "UTF-8");

            String params = "?to=" + targetLanguage + "&text=" + encodedText;
            URL url = new URL(host + "/V2/Http.svc/Translate" + params);

            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Ocp-Apim-Subscription-Key", subscriptionKey);
            connection.setDoOutput(true);

            StringBuilder response = new StringBuilder();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();
            return response.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        } catch (ProtocolException e) {
            e.printStackTrace();
            return null;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        try {
            JSONObject o = OCRInterface.analyseURL("https://www.welt.de/img/wirtschaft/mobile101101592/5112509217-ci102l-w1024/kcal2-DW-Wirtschaft-Michelau-jpg.jpg");
            List<String> l = OCRInterface.getText(o);

            for (String word : l) {
                System.out.println(translate(word));
            }


            String response = translate("Milch. Hafer. Weizen. Nüsse", "en");
            System.out.println(response);
            response = identify("Milch. Hafer. Weizen. Nüsse");
            System.out.println(response);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
