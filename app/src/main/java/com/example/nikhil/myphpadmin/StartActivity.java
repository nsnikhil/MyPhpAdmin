package com.example.nikhil.myphpadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity implements View.OnClickListener{

    Button viewItem,addItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initilize();
        setClickListener();
    }

    private void setClickListener() {
        viewItem.setOnClickListener(this);
        addItem.setOnClickListener(this);
    }

    private void initilize() {
        viewItem = (Button)findViewById(R.id.viewItems);
        addItem = (Button)findViewById(R.id.addItems);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.viewItems:
                startActivity(new Intent(StartActivity.this,ViewActivity.class));
                break;
            case R.id.addItems:
                startActivity(new Intent(StartActivity.this,AddActivity.class));
                break;
        }
    }
}
