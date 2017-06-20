package com.krivic.mismascotasc4.InstagramPresenter;



public interface IPerfilCuentaInstagramActivityPresentador {
    public void  obtenerMascotasCuentaInstagram();

    public void obtenerIdbyUsername(String Username);
    public void obtenerMediosRecientes();

    public void obtenerMediosRecientesbyId(String idUsername);

    public void  mostrarMascotasRV();
}
