package com.example.googlebookapi;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FetchBook extends AsyncTask<String, Void, String> {
    private TextView mTitle;
    private TextView mAuthor;

    public FetchBook(TextView mTitleText, TextView mAuthorText) {
        this.mTitle = mTitleText;
        this.mAuthor = mAuthorText;
    }

    @Override
    protected String doInBackground(String... strings) {
        return Utils.getBookInfo(strings[0]);

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        String title=null;
        String author=null;
        try {
            JSONObject jsonObject=new JSONObject(s);
            JSONArray itemsArray=jsonObject.getJSONArray("items");
            for (int i=0;i<itemsArray.length();i++){
                JSONObject book=itemsArray.getJSONObject(i);

                JSONObject voulumeInfo=book.getJSONObject("volumeInfo");
                title=voulumeInfo.getString("title");
                author=voulumeInfo.getString("authors");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (title!=null&&author!=null){
            mTitle.setText(title);
            mAuthor.setText(author);
            return;
        }
    }
}
