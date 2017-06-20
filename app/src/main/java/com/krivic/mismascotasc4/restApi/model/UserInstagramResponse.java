package com.krivic.mismascotasc4.restApi.model;

import com.krivic.mismascotasc4.pojo.UserInstagram;

import java.util.ArrayList;



public class UserInstagramResponse {

    ArrayList<UserInstagram> userInstagrams;

    public ArrayList<UserInstagram> getUserInstagrams() {
        return userInstagrams;
    }

    public void setUserInstagrams(ArrayList<UserInstagram> userInstagrams) {
        this.userInstagrams = userInstagrams;
    }
}
