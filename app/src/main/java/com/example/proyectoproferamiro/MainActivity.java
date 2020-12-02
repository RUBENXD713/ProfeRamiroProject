package com.example.proyectoproferamiro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView name,email;
    private RequestQueue requestQueue;
    private VolleyS mVolleyS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mVolleyS = VolleyS.getInstance(this.getApplicationContext());
        requestQueue = mVolleyS.getRequestQueue();

        name=findViewById(R.id.textView);
        email=findViewById(R.id.textView2);
    }

    @Override
    public void onClick(View v) {
        String url = "http://192.168.1.71:8000/api/datos";
        int id=1;
        JsonObjectRequest Json = new JsonObjectRequest(Request.Method.GET, url,id, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                        name.setText(response.getInt("numero"));
                        email.setText(response.getInt("email"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(Json);
    }
}