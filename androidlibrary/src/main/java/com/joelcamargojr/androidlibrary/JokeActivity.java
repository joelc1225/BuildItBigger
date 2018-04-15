package com.joelcamargojr.androidlibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        TextView jokeTv = (TextView) findViewById(R.id.jokeTv);

        Intent receivedIntent = getIntent();
        String jokeString = receivedIntent.getStringExtra("jokeKey");

        if (jokeString != null) {
            jokeTv.setText(jokeString);
        } else {
            jokeTv.setText(R.string.jokeError);
        }


    }
}
