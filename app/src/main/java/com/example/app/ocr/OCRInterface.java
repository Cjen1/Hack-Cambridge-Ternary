package com.example.app.ocr;// This sample uses the Apache HTTP client library(org.apache.httpcomponents:httpclient:4.2.4)
// and the org.json library (org.json:json:20170516).

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class OCRInterface
{
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
                end = s.indexOf('"',pos+7);
                String word = s.substring(pos+7,end);
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
        FileEntity fileEntity = new FileEntity(f, ContentType.APPLICATION_OCTET_STREAM);
        JSONObject result = serverRequest(fileEntity,"application/octet-stream");
        return result;
    }

    public static List<String> analyseLocalToText(String imgUrl) {
        File f = new File(imgUrl);
        FileEntity fileEntity = new FileEntity(f, ContentType.APPLICATION_OCTET_STREAM);
        JSONObject result = serverRequest(fileEntity,"application/octet-stream");
        List<String> l = getText(result);

        return l;
    }

    public static JSONObject analyseURL(String url) {
        try {
            StringEntity requestEntity =
                    new StringEntity("{\"url\":\"" + url + "\"}");
            JSONObject result = serverRequest(requestEntity, "application/json");
            return result;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static JSONObject serverRequest(AbstractHttpEntity fileEntity, String contentType) {
        HttpClient httpClient = new DefaultHttpClient();

        try
        {
            // NOTE: You must use the same location in your REST call as you used to obtain your subscription keys.
            //   For example, if you obtained your subscription keys from westus, replace "westcentralus" in the
            //   URL below with "westus".
            URIBuilder uriBuilder = new URIBuilder(uriBase);

            uriBuilder.setParameter("language", "unk");
            uriBuilder.setParameter("detectOrientation ", "true");

            // Request parameters.
            URI uri = uriBuilder.build();
            HttpPost request = new HttpPost(uri);

            // Request headers.
            request.setHeader("Content-Type", contentType);
            request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);

            // Request body.
            request.setEntity(fileEntity);

            // Execute the REST API call and get the response entity.
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null)
            {
                // Format and display the JSON response.
                String jsonString = EntityUtils.toString(entity);
                JSONObject json = new JSONObject(jsonString);
                //System.out.println("REST Response:\n");
                //System.out.println(json.toString(2));
                return json;
            } else {
                return null;
            }
        }
        catch (Exception e)
        {
            // Display error message.
            System.out.println(e.getMessage());
            return null;
        }



    }

}