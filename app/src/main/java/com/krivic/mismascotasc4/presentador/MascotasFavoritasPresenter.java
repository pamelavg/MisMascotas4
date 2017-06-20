package com.krivic.mismascotasc4.presentador;

import android.content.Context;

import com.krivic.mismascotasc4.MascotasFavoritas.IMascotasFavoritasView;
import com.krivic.mismascotasc4.db.ConstructorMascotas;
import com.krivic.mismascotasc4.pojo.Mascota;

import java.util.ArrayList;



public class MascotasFavoritasPresenter implements IMascotasFavoritasPresenter {

    private IMascotasFavoritasView iMascotasFavoritasView;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;

     public MascotasFavoritasPresenter(IMascotasFavoritasView iMascotasFavoritasView, Context context){
        this.iMascotasFavoritasView = iMascotasFavoritasView;
        this.context = context;
         obtenerMascotasFavoritasBaseDatos();
     }

    public MascotasFavoritasPresenter(IMascotasFavoritasView iMascotasFavoritasView){
        this.iMascotasFavoritasView = iMascotasFavoritasView;
        obtenerMascotasFavoritasBaseDatos();
    }

    @Override
    public void obtenerMascotasFavoritasBaseDatos() {
        constructorMascotas = new ConstructorMascotas(context);
        mascotas = constructorMascotas.obtenerDatosFavoritas();
        mostrarMascotasRV();
    }

    @Override
    public void mostrarMascotasRV() {
        iMascotasFavoritasView.inicializarAdaptadorRV(iMascotasFavoritasView.crearAdaptador(mascotas));
        iMascotasFavoritasView.generarLinearLayoutVertical();

    }
}
