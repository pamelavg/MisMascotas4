package com.krivic.mismascotasc4.Instagram;

import com.krivic.mismascotasc4.adapter.MascotaAdaptadorPerfilInstagram;
import com.krivic.mismascotasc4.pojo.MascotaInstagram;

import java.util.ArrayList;



public interface IPerfilCuentaInstagramActivityView {

    public final String idUsername = "";

    public void generalGridLayout();

    public MascotaAdaptadorPerfilInstagram crearAdaptador(ArrayList<MascotaInstagram> mascotasInstagrams);

    public void inicializarAdaptadorRV(MascotaAdaptadorPerfilInstagram adaptadorPerfil);

    public void llenaEncabezado(ArrayList<MascotaInstagram> mascotasInstagrams);
}
