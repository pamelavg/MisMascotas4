package com.krivic.mismascotasc4.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.krivic.mismascotasc4.R;
import com.krivic.mismascotasc4.pojo.Mascota;

import java.util.ArrayList;



public class MascotaAdaptadorPerfil extends RecyclerView.Adapter<MascotaAdaptadorPerfil.MascotaViewHolder> {
    ArrayList<Mascota> mascotas;
    Activity activity;

    public MascotaAdaptadorPerfil(ArrayList<Mascota> mascotas, Activity activity){
        this.mascotas = mascotas;
        this.activity = activity;
    }



    @Override
    public MascotaAdaptadorPerfil.MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota_perfil, parent, false);
        return new MascotaAdaptadorPerfil.MascotaViewHolder(v);
    }



    @Override
    public void onBindViewHolder(final MascotaAdaptadorPerfil.MascotaViewHolder mascotaViewHolder, int position) {
        final Mascota mascota = mascotas.get(position);
        mascotaViewHolder.imgFoto.setImageResource(mascota.getFoto());
        mascotaViewHolder.tvraitingcv.setText(String.valueOf(mascota.getRaiting()));
    }

    @Override
    public int getItemCount() {//cantidad de elementos que contiene mi lista de contactos
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgFoto;
        private TextView  tvraitingcv;

        public MascotaViewHolder(View itemView) {
            super(itemView);
            imgFoto     = (ImageView) itemView.findViewById(R.id.imgMascotacv);
            tvraitingcv = (TextView) itemView.findViewById(R.id.tvTotRatingcv);
        }
    }

}
