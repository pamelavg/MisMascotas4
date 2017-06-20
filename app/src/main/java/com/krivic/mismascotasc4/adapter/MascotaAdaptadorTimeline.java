package com.krivic.mismascotasc4.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.krivic.mismascotasc4.Instagram.PerfilCuentaInstagramActivity;
import com.krivic.mismascotasc4.MainActivity;
import com.krivic.mismascotasc4.R;
import com.krivic.mismascotasc4.pojo.MascotaInstagram;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;



public class MascotaAdaptadorTimeline extends RecyclerView.Adapter <MascotaAdaptadorTimeline.MascotaViewHolder> {
    ArrayList<MascotaInstagram> mascotasInstagram;
    Activity activity;

    public MascotaAdaptadorTimeline(ArrayList<MascotaInstagram> mascotasInstagram, Activity activity){
        this.mascotasInstagram = mascotasInstagram;
        this.activity = activity;
    }


    @Override
    public MascotaAdaptadorTimeline.MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota_timeline, parent, false);
        return new MascotaAdaptadorTimeline.MascotaViewHolder(v);
    }



    @Override
    public void onBindViewHolder(final MascotaAdaptadorTimeline.MascotaViewHolder mascotaViewHolder, int position) {
        final MascotaInstagram mascotaInstagram = mascotasInstagram.get(position);

        Picasso.with(activity)
                .load(mascotaInstagram.getUrlfotoperfil())
                .placeholder(R.drawable.bolita)
                .into(mascotaViewHolder.imgFoto);

        mascotaViewHolder.tvNombrecv.setText(mascotaInstagram.getUsername());

           mascotaViewHolder.imgFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.USERNAME = mascotaInstagram.getUsername();
                Intent intent = new Intent(activity, PerfilCuentaInstagramActivity.class );
                activity.startActivity(intent);
                    }
              });


    }

    @Override
    public int getItemCount() {//cantidad de elementos que contiene mi lista de contactos
        return mascotasInstagram.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgFoto;
        private TextView tvNombrecv;

        public MascotaViewHolder(View itemView) {
            super(itemView);
            imgFoto     = (ImageView) itemView.findViewById(R.id.imgMascotacv);
            tvNombrecv  = (TextView) itemView.findViewById(R.id.tvNombreMascotacv);
          }
    }



}
