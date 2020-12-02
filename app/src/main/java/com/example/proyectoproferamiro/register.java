package com.example.proyectoproferamiro;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;
import android.os.Bundle;


public class register extends AppCompatActivity implements View.OnClickListener {
    private RequestQueue requestQueue;
    private VolleyS mVolleyS;
    Button botonRegistro;
    EditText nombreR,correoR,passwordR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        nombreR=(EditText) findViewById(R.id.name);
        correoR=(EditText) findViewById(R.id.email);
        passwordR=(EditText) findViewById(R.id.password);
        botonRegistro= (Button) findViewById(R.id.Registrar);

        mVolleyS = VolleyS.getInstance(this.getApplicationContext());
        requestQueue = mVolleyS.getRequestQueue();

    }

    @Override
    public void onClick(View v) {
        String urlEnv = "http://192.168.1.71:8000/api/register";

        JSONObject datos = new JSONObject();
        try {
            datos.put("name", R.id.name);
            datos.put("email", R.id.email);
            datos.put("password", R.id.password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, urlEnv, datos, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(register.this, response.toString(), Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
        setContentView(R.layout.activity_login);
    }
}
