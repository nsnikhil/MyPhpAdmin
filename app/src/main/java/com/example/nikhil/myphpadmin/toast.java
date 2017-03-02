package com.example.nikhil.myphpadmin;

import android.content.Context;
import android.widget.Toast;


public class toast {

    Context c;

    public toast(Context context){
         c = context;
    }

    public void show(String s){
        Toast.makeText(c,s,Toast.LENGTH_LONG).show();
    }
}
