package com.krivic.mismascotasc4.restApi.adapter;

import com.krivic.mismascotasc4.restApi.ConstantesRestApi;
import com.krivic.mismascotasc4.restApi.Deserializador.IdbyUserDeserializador;
import com.krivic.mismascotasc4.restApi.Deserializador.MascotaDeserializador;
import com.krivic.mismascotasc4.restApi.Deserializador.followsSelfDeserializador;
import com.krivic.mismascotasc4.restApi.EndpointsApi;
import com.krivic.mismascotasc4.restApi.model.MascotaResponse;
import com.krivic.mismascotasc4.restApi.model.UserInstagramResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class RestApiAdapter {

    public EndpointsApi establecerConexionRestApiInstagram (Gson gson){
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(EndpointsApi.class);
    }


public Gson construyeGsonDeserializadorMediaRecent(){
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.registerTypeAdapter(MascotaResponse.class, new MascotaDeserializador());

    return gsonBuilder.create();
}

    public Gson construyeGsonDeserializadorIdbyUsername() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(UserInstagramResponse.class, new IdbyUserDeserializador());

        return gsonBuilder.create();
    }

    public Gson construyeGsonDeserializadorfollwsself(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaResponse.class, new followsSelfDeserializador());

        return gsonBuilder.create();

    }


}


