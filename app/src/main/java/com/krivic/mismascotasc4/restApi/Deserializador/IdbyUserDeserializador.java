package com.krivic.mismascotasc4.restApi.Deserializador;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.krivic.mismascotasc4.pojo.UserInstagram;
import com.krivic.mismascotasc4.restApi.JsonKeys;
import com.krivic.mismascotasc4.restApi.model.UserInstagramResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;



public class IdbyUserDeserializador implements JsonDeserializer<UserInstagramResponse> {

    @Override
    public UserInstagramResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        UserInstagramResponse userInstagramResponse = gson.fromJson(json, UserInstagramResponse.class);
        JsonArray userInstagramResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.ID_RESPONSE_ARRAY);

        userInstagramResponse.setUserInstagrams(deserializarUserInstagramDeJson(userInstagramResponseData));
        return userInstagramResponse;
    }

    private ArrayList<UserInstagram> deserializarUserInstagramDeJson(JsonArray userInstagramResponseData){
        ArrayList<UserInstagram> userInstagrams = new ArrayList<>();

        for (int i=0; i < userInstagramResponseData.size(); i++){
            JsonObject userInstagramResponseDataObject = userInstagramResponseData.get(i).getAsJsonObject();
            String id                  = userInstagramResponseDataObject.get(JsonKeys.USER_ID).getAsString();
            String username            = userInstagramResponseDataObject.get(JsonKeys.USER_USERNAME).getAsString();

            UserInstagram userInstagramActual = new UserInstagram();
            userInstagramActual.setId(id);
            userInstagramActual.setUsername(username);


            userInstagrams.add(userInstagramActual);
        }
        return userInstagrams;

    }
}
