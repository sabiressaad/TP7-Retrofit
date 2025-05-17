package com.example.tp_retrofit.src.network;

import com.example.tp_retrofit.src.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("users")
    Call<List<User>> getUsers();

}
