package com.example.exoprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }
    public void share(View view) {
        String url = "https://www.instagram.com/" ;
        Intent bukaig = new Intent(Intent. ACTION_VIEW);
        bukaig.setData(Uri. parse(url));
        startActivity(bukaig);
    }
}