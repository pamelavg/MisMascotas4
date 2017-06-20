package com.krivic.mismascotasc4.restApi.model;

import com.krivic.mismascotasc4.pojo.MascotaInstagram;

import java.util.ArrayList;


public class MascotaResponse {

    ArrayList<MascotaInstagram> mascotasInstagram;

    public ArrayList<MascotaInstagram> getMascotasInstagram()
    {
        return mascotasInstagram;
    }

    public void setMascotasInstagram(ArrayList<MascotaInstagram> mascotasInstagram) {
        this.mascotasInstagram = mascotasInstagram;
    }
}
