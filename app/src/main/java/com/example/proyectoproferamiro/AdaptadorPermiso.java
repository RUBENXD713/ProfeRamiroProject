package com.example.proyectoproferamiro;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorPermiso extends RecyclerView.Adapter<AdaptadorPermiso.Miholder> implements View.OnClickListener{

    private List<Permiso> ListaPermiso;
    private View.OnClickListener Listener;
    private Activity activity;

    public AdaptadorPermiso(List<Permiso> listaPermiso,Activity activity) {
        ListaPermiso = listaPermiso;
        this.activity=activity;
    }

    @NonNull
    @Override
    public Miholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vistaPermiso = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_permiso,parent,false);
        vistaPermiso.findViewById(R.id.swPermiso).setOnClickListener(this);
        return new Miholder(vistaPermiso);
    }

    @Override
    public void onBindViewHolder(@NonNull Miholder holder, int position) {

        Permiso modelo = ListaPermiso.get(position);
        holder.setData(modelo,activity);
    }

    @Override
    public int getItemCount() {
        return ListaPermiso.size();
    }


    public void setOnClicListener(View.OnClickListener listener){
        this.Listener = listener;
    }
    @Override
    public void onClick(View view) {
        if (Listener != null){
            Listener.onClick(view);
        }
    }

    public class Miholder extends RecyclerView.ViewHolder {

        private Switch swPermiso;
        public String permisoCompleto;//guarda permiso solicitado
        public Activity activity;

        public Miholder(@NonNull final View itemView) {
            super(itemView);
            swPermiso = itemView.findViewById(R.id.swPermiso);
            swPermiso.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        int permiso= ActivityCompat.checkSelfPermission(itemView.getContext(),permisoCompleto);
                        if(permiso!= PackageManager.PERMISSION_GRANTED){
                            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                                ActivityCompat.requestPermissions(activity,new String[]{permisoCompleto},26);
                            return;
                            }
                        }
                        Toast.makeText(itemView.getContext(),"El Permiso a sido consedido",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }



        public void setData(final Permiso modelo, Activity activity) {
            swPermiso.setChecked(modelo.getAcceso());
            swPermiso.setText(modelo.getPermiso());
            permisoCompleto=modelo.getPeermisoReal();
            this.activity=activity;
        }
    }
}
