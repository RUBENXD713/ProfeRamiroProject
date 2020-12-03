package com.example.proyectoproferamiro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Checkable;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.RequestQueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.content.Intent.ACTION_CALL;

public class PermisosActivity extends AppCompatActivity implements View.OnClickListener {

    Switch swPermisoInternet;
    Switch swPermisoLlamar;
    Switch swPermisoCamara;
    private RecyclerView RVPermiso;

    final private int ventana = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permisos);

        findViewById(R.id.btnLogin).setOnClickListener(this);

        solicitarPermiso();

        RVPermiso = findViewById(R.id.rvPermisos);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        RVPermiso.setLayoutManager(layoutManager);

        final List<Permiso> ListaPermisos = new ArrayList<>();
        ListaPermisos.add(new Permiso("localizacion",false, Manifest.permission.ACCESS_FINE_LOCATION));
        ListaPermisos.add(new Permiso("Llamadas",false, Manifest.permission.CALL_PHONE));
        ListaPermisos.add(new Permiso("Camara",false, Manifest.permission.CAMERA));
        ListaPermisos.add(new Permiso("Almacenamiento",false, Manifest.permission.READ_EXTERNAL_STORAGE));

        final AdaptadorPermiso Permisos = new AdaptadorPermiso(ListaPermisos,this);

        RVPermiso.setAdapter(Permisos);

    }

    private void solicitarPermiso() {

    }
    private void hacerLlamada() {
        startActivity(new Intent(ACTION_CALL, Uri.parse("tel:8713452055")));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        Log.i("permisos_nombre", Arrays.deepToString(permissions));
        Log.i("permisos_acceso", Arrays.toString(grantResults));

        if (requestCode==ventana){
            if (permissions.length>=1){
                int acceso=-1;
                for (int permiso:grantResults){
                    permiso = acceso;
                    if (permiso == PackageManager.PERMISSION_DENIED)
                        break;
                }
                if (acceso == PackageManager.PERMISSION_GRANTED)
                    hacerLlamada();
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnLogin){
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }
    }

}