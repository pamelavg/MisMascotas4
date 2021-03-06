package com.krivic.mismascotasc4.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.krivic.mismascotasc4.R;
import com.krivic.mismascotasc4.adapter.MascotaAdaptadorPerfil;
import com.krivic.mismascotasc4.pojo.Mascota;

import java.util.ArrayList;



public class PerfilFragment extends Fragment {
    ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;
    public MascotaAdaptadorPerfil adaptador;

    public PerfilFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_perfil, container, false);


        listaMascotas = (RecyclerView) v.findViewById(R.id.rvMascotas);

        GridLayoutManager glm = new GridLayoutManager(getActivity(),3);
        glm.setOrientation(GridLayoutManager.VERTICAL);

        listaMascotas.setLayoutManager(glm);
        inicializarListaMascotas();
        inicializarAdaptador();

        return v;
    }

    public void inicializarAdaptador() {
        adaptador = new MascotaAdaptadorPerfil(mascotas, getActivity());
        listaMascotas.setAdapter(adaptador);
    }

    public void inicializarListaMascotas() {
        mascotas = new ArrayList<Mascota>();

        mascotas.add(new Mascota(R.drawable.connor1, "Connor", 1));
        mascotas.add(new Mascota(R.drawable.connor2, "Bolita", 2));
        mascotas.add(new Mascota(R.drawable.connor3, "Bunny", 3));
        mascotas.add(new Mascota(R.drawable.connor4, "Paloma", 4));
        mascotas.add(new Mascota(R.drawable.connor5, "Shuly", 5));
        mascotas.add(new Mascota(R.drawable.connor6, "Shuly", 4));
        mascotas.add(new Mascota(R.drawable.connor7, "Shuly", 3));
        mascotas.add(new Mascota(R.drawable.connor8, "Shuly", 2));
        mascotas.add(new Mascota(R.drawable.connor9, "Shuly", 1));
    }

}
