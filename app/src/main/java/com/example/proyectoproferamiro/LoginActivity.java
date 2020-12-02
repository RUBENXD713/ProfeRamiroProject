package com.example.proyectoproferamiro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText email,emailR2, password,passwordR1,passwordR2, user;
    private RequestQueue Elcartero;
    private VolleyS mVolleyS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.Iniciar).setOnClickListener(this);
        findViewById(R.id.Registrar).setOnClickListener(this);

        mVolleyS = VolleyS.getInstance(this.getApplicationContext());
        Elcartero = mVolleyS.getRequestQueue();

        email=findViewById(R.id.email);
        //emailR2=findViewById(R.id.emailR1);
        password=findViewById(R.id.password);
        //passwordR1=findViewById(R.id.passwordR);
        //passwordR2=findViewById(R.id.passwordR2);
        //user=findViewById(R.id.name);
    }

    @Override
    public void onClick(View view) {
        //JSONObject datos=new JSONObject();
        switch (view.getId()){
            case R.id.Iniciar:
                String urlEnv = "http://192.168.1.71:8000/api/login";

                JSONObject datos = new JSONObject();
                try {
                    datos.put("password",password.getText().toString());
                    datos.put("email",email.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest puntajeJson = new JsonObjectRequest(Request.Method.POST, urlEnv, (JSONObject) datos, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        Toast.makeText(LoginActivity.this, response.toString(), Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
                Elcartero.add(puntajeJson);
                setContentView(R.layout.usuario);

                //startActivity(new Intent(getApplicationContext(), MainActivity.class));
               /* try {
                    datos.put("email", email.getText().toString());
                    datos.put("password", password.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest UserJson = new JsonObjectRequest(Request.Method.POST, "http::/127.0.0.1:8000/api/login", datos, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        int validate= 0;

                        try {
                            validate = response.getInt("Validacion");
                            email.setText(validate);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if(validate == 1){
                            //setContentView(R.layout.User);
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

                //startActivity(new Intent(getApplicationContext(), MainActivity.class));*/
                break;
            case R.id.Registrar:
                setContentView(R.layout.activity_registro);
                //Toast.makeText(getApplicationContext(), "Lo sentimos aun no esta disponible.", Toast.LENGTH_LONG).show();
                break;
        }

    }
}