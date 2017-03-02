package com.example.nikhil.myphpadmin;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AddActivity extends AppCompatActivity {

    EditText name,price;
    Button submit;
    toast tst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initilize();
    }

    private void initilize() {
        name = (EditText)findViewById(R.id.name);
        price = (EditText)findViewById(R.id.price);
        submit = (Button) findViewById(R.id.submit);
        tst = new toast(getApplicationContext());
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verify()){
                    Log.d("Url",buildUri());
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest requestString = new StringRequest(Request.Method.GET, buildUri(), new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            tst.show(response);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            tst.show("Error");
                        }
                    });
                    requestQueue.add(requestString);
                }
            }
        });
    }




    private String buildUri(){
        String s = "http://192.168.2.7/insertph.php";
        String nameQuery = "nm";
        String nameValue = name.getText().toString();
        String priceQuery = "pr";
        String priceValue = price.getText().toString();
        return  Uri.parse(s).buildUpon().appendQueryParameter(nameQuery,nameValue).appendQueryParameter(priceQuery,priceValue).build().toString();
    }

    private boolean verify(){
        if(name.getText().toString()==null||name.getText().toString().length()==0){
            tst.show("Enter the item name");
            return false;
        }if(price.getText().toString()==null||price.getText().toString().length()==0){
            tst.show("Enter the item price");
            return false;
        }
        return true;
    }

}
