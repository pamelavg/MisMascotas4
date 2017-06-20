package com.krivic.mismascotasc4.MascotasFavoritas;

import com.krivic.mismascotasc4.adapter.MascotaAdaptadorFavoritas;
import com.krivic.mismascotasc4.pojo.Mascota;

import java.util.ArrayList;



public interface IMascotasFavoritasView {

    public void generarLinearLayoutVertical ();

    public MascotaAdaptadorFavoritas crearAdaptador(ArrayList<Mascota> mascotas);

    public void inicializarAdaptadorRV(MascotaAdaptadorFavoritas adaptador);

}
