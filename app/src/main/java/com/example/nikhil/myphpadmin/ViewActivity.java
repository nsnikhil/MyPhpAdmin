package com.example.nikhil.myphpadmin;

import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {

    ListView allItemList;
    toast tst = null;
    ArrayList<String> listItem = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        initilize();
        viewAll();
    }

    private void initilize() {
        allItemList = (ListView) findViewById(R.id.allItemsList);
        tst = new toast(getApplicationContext());
    }

    private void viewAll() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.2.7/query.php";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    addToList(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tst.show("error");
            }
        });
        queue.add(jsonArrayRequest);
    }

    private void addToList(JSONArray response) throws JSONException {
        for(int i=0;i<response.length();i++){
            JSONObject jsObj = response.getJSONObject(i);
            String name = jsObj.getString("name");
            int price = jsObj.getInt("price");
            listItem.add(name+" "+price);
        }
        ArrayAdapter<String> adap = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,listItem);
        allItemList.setAdapter(adap);
    }
}
