package com.ifreecomm.titlebargradualchange;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity{

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void scrollView(View view) {
        Intent intent = new Intent(this,ScrollViewActivity.class);
        startActivity(intent);
    }

    public void listView(View view) {
        Intent intent = new Intent(this,ListActivity.class);
        startActivity(intent);
    }

    public void recyclerView(View view) {
        Intent intent = new Intent(this,RecyclerActivity.class);
        startActivity(intent);
    }
}
