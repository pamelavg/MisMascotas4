package com.krivic.mismascotasc4.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.krivic.mismascotasc4.R;
import com.krivic.mismascotasc4.db.ConstructorMascotas;
import com.krivic.mismascotasc4.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by krivic on 02/06/2017.
 */
/////////// Adaptador del RecyclerWiew y CardView////////
public class MascotaAdaptador extends RecyclerView.Adapter <MascotaAdaptador.MascotaViewHolder> {
    ArrayList<Mascota> mascotas;
    Activity activity;

    public MascotaAdaptador(ArrayList<Mascota> mascotas, Activity activity){
        this.mascotas = mascotas;
        this.activity = activity;
    }


    @Override
    public  MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota, parent, false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MascotaViewHolder mascotaViewHolder, int position) {
        final Mascota mascota = mascotas.get(position);
        mascotaViewHolder.imgFoto.setImageResource(mascota.getFoto());
        mascotaViewHolder.tvNombrecv.setText(mascota.getNombre());

        ConstructorMascotas constructorMascotas = new ConstructorMascotas(activity);
        mascotaViewHolder.tvraitingcv.setText(String.valueOf(constructorMascotas.obtenerLikesMascota(mascota)));

        mascotaViewHolder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i;
                //Toast.makeText(activity, "Te gusto" + mascota.getNombre() + mascota.getRaiting(), Toast.LENGTH_SHORT).show();

                ConstructorMascotas constructorMascotas = new ConstructorMascotas(activity);
                constructorMascotas.darLikeMascota(mascota);
                mascotaViewHolder.tvraitingcv.setText(String.valueOf(constructorMascotas.obtenerLikesMascota(mascota)));
            }
        });

        mascotaViewHolder.btnMuestraRaiting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConstructorMascotas constructorMascotas = new ConstructorMascotas(activity);
             mascotaViewHolder.tvraitingcv.setText(String.valueOf(constructorMascotas.obtenerLikesMascota(mascota)));
            }
        });

        //   mascotaViewHolder.imgFoto.setOnClickListener(new View.OnClickListener() {
        //    public void onClick(View v) {
        //        Intent intent = new Intent(activity, MascotasFavoritas.class );
        //         intent.putExtra("foto", mascota.getFoto());
        //        intent.putExtra ("nombre", mascota.getNombre());
         //      intent.putExtra("raiting", mascota.getRaiting());
        //      activity.startActivity(intent);
        //            }
        //      });
    }

    @Override
    public int getItemCount() {//cantidad de elementos que contiene mi lista de contactos
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgFoto;
        private TextView tvNombrecv;
        private TextView  tvraitingcv;
        private ImageButton btnLike;
        private ImageButton btnMuestraRaiting;

        public MascotaViewHolder(View itemView) {
            super(itemView);
            imgFoto     = (ImageView) itemView.findViewById(R.id.imgMascotacv);
            tvNombrecv  = (TextView) itemView.findViewById(R.id.tvNombreMascotacv);
            tvraitingcv = (TextView) itemView.findViewById(R.id.tvTotRatingcv);
            btnLike     = (ImageButton) itemView.findViewById(R.id.hueso_blancocv);
            btnMuestraRaiting =(ImageButton) itemView.findViewById(R.id.hueso_amarillocv);
        }
    }


}
