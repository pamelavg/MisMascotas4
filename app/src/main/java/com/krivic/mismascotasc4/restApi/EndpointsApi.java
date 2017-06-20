package com.krivic.mismascotasc4.restApi;

import com.krivic.mismascotasc4.restApi.model.MascotaResponse;
import com.krivic.mismascotasc4.restApi.model.UserInstagramResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;



public interface EndpointsApi {


    // https://api.instagram.com/v1/users/self/media/recent/?access_token=ACCESS-TOKEN
    @GET(com.krivic.mismascotasc4.restApi.ConstantesRestApi.URL_GET_RECENT_MEDIA_USER)
    Call<MascotaResponse> getRecentMedia();


    // https://api.instagram.com/v1/users/{user-id}/media/recent/?access_token=ACCESS-TOKEN
    @GET
    public Call<MascotaResponse> getRecentMediaid(@Url String url);


// https://api.instagram.com/v1/users/search?q=jack&access_token=ACCESS-TOKEN
    @GET
    public Call<UserInstagramResponse> getIdbyUsername(@Url String url);


    //https://api.instagram.com/v1/users/self/follows?access_token=ACCESS-TOKEN

    @GET (com.krivic.mismascotasc4.restApi.ConstantesRestApi.KEY2_GET_FOLLOWS_SELF)
    Call<MascotaResponse> getfollowsself();

}
