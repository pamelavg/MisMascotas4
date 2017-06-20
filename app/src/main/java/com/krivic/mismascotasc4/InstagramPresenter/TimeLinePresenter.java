package com.krivic.mismascotasc4.InstagramPresenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.krivic.mismascotasc4.Instagram.ITimeLineView;
import com.krivic.mismascotasc4.db.ConstructorMascotas;
import com.krivic.mismascotasc4.pojo.MascotaInstagram;
import com.krivic.mismascotasc4.restApi.EndpointsApi;
import com.krivic.mismascotasc4.restApi.adapter.RestApiAdapter;
import com.krivic.mismascotasc4.restApi.model.MascotaResponse;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TimeLinePresenter implements com.krivic.mismascotasc4.InstagramPresenter.ITimeLinePresenter {

    private ITimeLineView iTimeLineView;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<MascotaInstagram> mascotasInstagrams;

    public TimeLinePresenter(ITimeLineView iTimeLineView, Context context) {
        this.iTimeLineView = iTimeLineView;
        this.context = context;
        obtenerTimeLineInstagram();
    }

    public TimeLinePresenter(ITimeLineView iTimeLineView) {
        this.iTimeLineView = iTimeLineView;
        obtenerTimeLineInstagram();
    }


    @Override
    public void obtenerTimeLineInstagram() {

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonfollowself = restApiAdapter.construyeGsonDeserializadorfollwsself();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonfollowself);
        Call<MascotaResponse> mascotaResponseCall = endpointsApi.getfollowsself();
        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();
                mascotasInstagrams = mascotaResponse.getMascotasInstagram();
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
        iTimeLineView.inicializarAdaptadorRV(iTimeLineView.crearAdaptador(mascotasInstagrams));
        iTimeLineView.generalLinearLayout();


    }
}
