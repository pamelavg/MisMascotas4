package com.krivic.mismascotasc4.InstagramPresenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.krivic.mismascotasc4.Instagram.IPerfilCuentaInstagramActivityView;
import com.krivic.mismascotasc4.MainActivity;
import com.krivic.mismascotasc4.db.ConstructorUserInstagram;
import com.krivic.mismascotasc4.pojo.MascotaInstagram;
import com.krivic.mismascotasc4.pojo.UserInstagram;
import com.krivic.mismascotasc4.restApi.ConstantesRestApi;
import com.krivic.mismascotasc4.restApi.EndpointsApi;
import com.krivic.mismascotasc4.restApi.adapter.RestApiAdapter;
import com.krivic.mismascotasc4.restApi.model.MascotaResponse;
import com.krivic.mismascotasc4.restApi.model.UserInstagramResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilCuentaInstagramActivityPresenter implements IPerfilCuentaInstagramActivityPresentador{

    private IPerfilCuentaInstagramActivityView iPerfilCuentaInstagramActivityView;
    private Context context;
    private ConstructorUserInstagram constructorUserInstagram;
    private ArrayList<MascotaInstagram> mascotasInstagram;
    private ArrayList<UserInstagram> userInstagrams;

    public String Username = "";
    public String idUsername = "";

    public PerfilCuentaInstagramActivityPresenter(IPerfilCuentaInstagramActivityView iPerfilCuentaInstagramActivityView, Context context) {
        this.iPerfilCuentaInstagramActivityView = iPerfilCuentaInstagramActivityView;
        this.context = context;

        Username = MainActivity.USERNAME;
        obtenerIdbyUsername(Username);
    }


    public PerfilCuentaInstagramActivityPresenter(IPerfilCuentaInstagramActivityView iPerfilCuentaInstagramActivityView) {
        this.iPerfilCuentaInstagramActivityView = iPerfilCuentaInstagramActivityView;


        Username = MainActivity.USERNAME;
        obtenerIdbyUsername(Username);
     }

    @Override
    public void obtenerMascotasCuentaInstagram() {

        constructorUserInstagram = new ConstructorUserInstagram(context);
        mascotasInstagram = constructorUserInstagram.obtenerDatos();
        mostrarMascotasRV();
    }
    @Override
    public void obtenerMediosRecientes() {


        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
        Call<MascotaResponse> mascotaResponseCall = endpointsApi.getRecentMedia();
        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                  MascotaResponse mascotaResponse = response.body();
                  mascotasInstagram = mascotaResponse.getMascotasInstagram();
                  mostrarMascotasRV();
            }
            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "No hubo conexión! Intente nuevamente! ", Toast.LENGTH_LONG).show();
                Log.e("CONEXION FALLIDA ", t.toString());
            }
        });
    }
    @Override
    public void obtenerIdbyUsername(String Username) {
        //Id de cuenta buscando el username
        String url;
        url = ConstantesRestApi.KEY1_GET_ID_DE_USERNAME + Username + ConstantesRestApi.KEY3_GET_ID_DE_USERNAME;
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonIdbyUsername = restApiAdapter.construyeGsonDeserializadorIdbyUsername();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonIdbyUsername);
        Call<UserInstagramResponse> userInstagramResponseCall = endpointsApi.getIdbyUsername(url);
        userInstagramResponseCall.enqueue(new Callback<UserInstagramResponse>() {
            @Override
            public void onResponse(Call<UserInstagramResponse> call, Response<UserInstagramResponse> response) {
                UserInstagramResponse userInstagramResponse = response.body();
                userInstagrams = userInstagramResponse.getUserInstagrams();
                idUsername = userInstagrams.get(0).getId();
                obtenerMediosRecientesbyId(idUsername);
            }
            @Override
            public void onFailure(Call<UserInstagramResponse> call, Throwable t) {
                Toast.makeText(context, "No hubo conexión! Intente nuevamente! ", Toast.LENGTH_LONG).show();
                Log.e("CONEXION FALLIDA ", t.toString());
            }
        });
    }


    @Override
    public void obtenerMediosRecientesbyId(String idUsername) {
        String url;

        url = ConstantesRestApi.KEY1_GET_USER_ID_MEDIA + idUsername + ConstantesRestApi.KEY4_GET_USER_ID_MEDIA;
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecent();

        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
        Call<MascotaResponse> mascotaResponseCall = endpointsApi.getRecentMediaid(url);

        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();
                mascotasInstagram = mascotaResponse.getMascotasInstagram();
                mostrarMascotasRV();
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "No hubo conexión! Intente nuevamente! ", Toast.LENGTH_LONG).show();
                Log.e("CONEXION FALLIDA ", t.toString());
            }
        });
    }

    @Override
    public void mostrarMascotasRV() {
        iPerfilCuentaInstagramActivityView.llenaEncabezado(mascotasInstagram);
        iPerfilCuentaInstagramActivityView.inicializarAdaptadorRV(iPerfilCuentaInstagramActivityView.crearAdaptador(mascotasInstagram));
        iPerfilCuentaInstagramActivityView.generalGridLayout();


    }

}
