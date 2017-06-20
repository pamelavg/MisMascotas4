package com.krivic.mismascotasc4.restApi.Deserializador;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.krivic.mismascotasc4.pojo.MascotaInstagram;
import com.krivic.mismascotasc4.restApi.JsonKeys;
import com.krivic.mismascotasc4.restApi.model.MascotaResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;



public class followsSelfDeserializador implements JsonDeserializer<MascotaResponse> {
    @Override
    public MascotaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        MascotaResponse mascotaResponse = gson.fromJson(json, MascotaResponse.class);
        JsonArray mascotaResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.ID_RESPONSE_ARRAY);

        mascotaResponse.setMascotasInstagram(deserializarUserInstagramDeJson(mascotaResponseData));
        return mascotaResponse;
    }

    private ArrayList<MascotaInstagram> deserializarUserInstagramDeJson(JsonArray mascotaResponseData){
        ArrayList<MascotaInstagram> mascotaInstagrams = new ArrayList<>();

        for (int i=0; i < mascotaResponseData.size(); i++){
            JsonObject mascotaResponseDataObject = mascotaResponseData.get(i).getAsJsonObject();
            String id                  = mascotaResponseDataObject.get(JsonKeys.USER_ID).getAsString();
            String username            = mascotaResponseDataObject.get(JsonKeys.USER_USERNAME).getAsString();
            String profile_picture     = mascotaResponseDataObject.get(JsonKeys.MEDIA_URL_PERFIL).getAsString();

            MascotaInstagram mascotaInstagramActual = new MascotaInstagram();
            mascotaInstagramActual.setId(id);
            mascotaInstagramActual.setUsername(username);
            mascotaInstagramActual.setUrlfotoperfil(profile_picture);
            mascotaInstagramActual.setLikes(0);


            mascotaInstagrams.add(mascotaInstagramActual);
        }
        return mascotaInstagrams;

    }
}
