package com.krivic.mismascotasc4.Instagram;

import com.krivic.mismascotasc4.adapter.MascotaAdaptadorFavoritas;
import com.krivic.mismascotasc4.adapter.MascotaAdaptadorPerfilInstagram;
import com.krivic.mismascotasc4.adapter.MascotaAdaptadorTimeline;
import com.krivic.mismascotasc4.pojo.Mascota;
import com.krivic.mismascotasc4.pojo.MascotaInstagram;

import java.util.ArrayList;



public interface ITimeLineView {
    public void generalLinearLayout();

    public MascotaAdaptadorTimeline crearAdaptador(ArrayList<MascotaInstagram> mascotasInstagrams);

    public void inicializarAdaptadorRV(MascotaAdaptadorTimeline adaptador);

}
