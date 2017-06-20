package com.krivic.mismascotasc4.fragments;

import com.krivic.mismascotasc4.adapter.MascotaAdaptador;
import com.krivic.mismascotasc4.pojo.Mascota;

import java.util.ArrayList;



public interface Ilista_mascotas_fragment_View {

    public void generarLinearLayoutVertical ();

    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas);

    public void inicializarAdaptadorRV(MascotaAdaptador adaptador);

}
