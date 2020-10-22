package com.example.googlebookapi;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static final String LOG_TAG = Utils.class.getSimpleName();
    //BaseURL for the Book API
    private static final String BOOK_BASE_URL = "https://www.googleapis.com/books/v1/volumes?";
    private static final String QUERY_PARAMS = "q";
    private static final String MAX_RESULTS = "maxResults";
    private static final String PRINT_TYPE = "printType";

    static String getBookInfo(String query) {
        HttpURLConnection http = null;
        BufferedReader reader = null;
        String bookJSONString = null;

        try {
            Uri builtUri = Uri.parse(BOOK_BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAMS, query)
                    .appendQueryParameter(MAX_RESULTS, "10")
                    .appendQueryParameter(PRINT_TYPE, "books")
                    .build();

            URL requestURL = new URL(builtUri.toString());

            http = (HttpURLConnection) requestURL.openConnection();
            http.setRequestMethod("GET");
            http.connect();

            //reading buffer
            InputStream inputStream=http.getInputStream();
            StringBuffer buffer=new StringBuffer();
            if (inputStream==null){
                return null;
            }
            reader=new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine())!=null){
                buffer.append(line+"\n");
            }
            if (buffer.length()==0){
                return null;
            }
            bookJSONString=buffer.toString();

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            if (http!=null){
                http.disconnect();
            }

            if (reader!=null){
                try {
                    reader.close();
                }catch (IOException iox){
                    iox.printStackTrace();
                }
            }
            Log.d(LOG_TAG,bookJSONString);
            return bookJSONString;
        }
    }

}
