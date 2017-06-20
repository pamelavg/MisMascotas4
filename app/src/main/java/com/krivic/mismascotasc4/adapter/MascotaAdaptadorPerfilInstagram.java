package com.krivic.mismascotasc4.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.krivic.mismascotasc4.R;
import com.krivic.mismascotasc4.pojo.MascotaInstagram;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MascotaAdaptadorPerfilInstagram extends RecyclerView.Adapter<MascotaAdaptadorPerfilInstagram.MascotaViewHolder>  {

    ArrayList<MascotaInstagram> mascotasInstagram;
    Activity activity;

    public MascotaAdaptadorPerfilInstagram(ArrayList<MascotaInstagram> mascotasInstagram, Activity activity){
        this.mascotasInstagram = mascotasInstagram;
        this.activity = activity;
    }


    @Override
    public MascotaAdaptadorPerfilInstagram.MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota_perfil, parent, false);
        return new MascotaAdaptadorPerfilInstagram.MascotaViewHolder(v);
    }



    @Override
    public void onBindViewHolder(final MascotaAdaptadorPerfilInstagram.MascotaViewHolder mascotaViewHolder, int position) {
        final MascotaInstagram mascotaInstagram = mascotasInstagram.get(position);

        Picasso.with(activity)
                .load(mascotaInstagram.getUrlfoto())
                .placeholder(R.drawable.bolita)
                .into(mascotaViewHolder.imgFoto);

        mascotaViewHolder.tvraitingcv.setText(String.valueOf(mascotaInstagram.getLikes()));
    }

    @Override
    public int getItemCount() {//cantidad de elementos que contiene mi lista de contactos
        return mascotasInstagram.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgFoto;
        private TextView tvraitingcv;

        public MascotaViewHolder(View itemView) {
            super(itemView);
            imgFoto     = (ImageView) itemView.findViewById(R.id.imgMascotacv);
            tvraitingcv = (TextView) itemView.findViewById(R.id.tvTotRatingcv);
        }
    }


}
