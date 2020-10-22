package com.example.googlebookapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private TextView tvAuthor,tvTitle;
    private  Button btn;
    private static final String TAG=MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.edittext);
        tvAuthor=findViewById(R.id.author_name);
        tvTitle=findViewById(R.id.book_name);
        btn=findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String queryString =editText.getText().toString();
                Log.i(TAG,"searchbooks: "+queryString);
                new FetchBook(tvTitle,tvAuthor).execute(queryString);
            }
        });
    }
}